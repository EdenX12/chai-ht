package com.tian.sakura.cdd.srv.config;

import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.srv.service.support.CustRedisService;
import com.tian.sakura.cdd.srv.service.support.OneNodeRedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class LettuceRedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> stringRedisTemplate;
    @Autowired
    private CustRedisService custRedisService;
    @Autowired
    private OneNodeRedisLock oneNodeRedisLock;

    @Test
    public void testStringRedit() {
        stringRedisTemplate.opsForValue().set("0416001", "0416001");

        String value = (String)stringRedisTemplate.opsForValue().get("0416001");

        System.out.println("key:0416001,value:" + value);

    }

    @Test
    public void testRedis() {

        custRedisService.set("0509001","023423", 60);
    }

    @Test
    public void tryLock() {
        String key = "lock-0509";
        String requestId = IdGenUtil.uuid();
        Boolean x = oneNodeRedisLock.tryLock(key,requestId,2,TimeUnit.MINUTES);
        System.out.println("获取锁"+ x);
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //oneNodeRedisLock.releaseLock(key, requestId);
        System.out.println(oneNodeRedisLock.get("lock-0509"));
        //启动线程 尝试获取锁
        Boolean y = oneNodeRedisLock.tryLock("lock-0509",IdGenUtil.uuid(),2,TimeUnit.MINUTES);
        System.out.println("获取锁"+ y);
    }
}
