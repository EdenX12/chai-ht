package com.tian.sakura.cdd.common.id;



import com.tian.sakura.cdd.common.util.DateUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

/**
 * 雪花算法
 *
 */
public class DefaultIdGenerator {

    private final long twepoch = 1288834974657L;
    //机器标识位数
    private final long workerIdBits = 5L;
    //数据中心标识位数
    private final long datacenterIdBits = 5L;
    //机器ID最大值
//	private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    //数据中心ID最大值
//	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //毫秒内自增位
    private final long sequenceBits = 12L;
    //机器ID偏左移12位
    private final long workerIdShift = sequenceBits;
    //数据中心ID左移17位
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    //时间毫秒左移22位
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    public long workerId;
    public long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private static DefaultIdGenerator instance = new DefaultIdGenerator();

    public static DefaultIdGenerator getInstance() {
        return instance;
    }

    /**
     * 生成uuid prefix+timestamp+18位
     * @param prefix 前缀
     * @param timestampFlag 时间戳
     * @return
     */
    public String genUUID(String prefix,boolean timestampFlag){
        StringBuffer uuid = new StringBuffer();
        if(prefix != null){
            uuid.append(prefix);
        }
        if(timestampFlag){
            uuid.append(DateUtils.formatDate(new Date(),"yyMMdd"));
        }
        uuid.append(nextId());
        return uuid.toString();
    }

    /**
     * 获取机器id
     * @return
     */
    private static long getMachineId() {
        long machineId = 0;
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    String ipAddress = addresses.nextElement().getHostAddress();
                    machineId = Math.abs(ipAddress.hashCode()%32);
                }
            }
        } catch (SocketException e) {
            return machineId;
        }
        return machineId;
    }

    public DefaultIdGenerator() {
//		if (workerId > maxWorkerId || workerId < 0) {
//			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
//		}
//		if (datacenterId > maxDatacenterId || datacenterId < 0) {
//			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
//		}
        this.workerId = getMachineId();
        this.datacenterId = 0;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        //时间错误
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            //当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                //当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        //ID偏移组合生成最终的ID，并返回ID
        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}
