package com.tian.sakura.cdd.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * base64工具类
 *
 */
public class Base64Utils {
 
	/**
	 * 本地图片转换成base64字符串
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 *
	 * @param imgFile	图片本地路径
	 * @return
	 */
	public static String ImageToBase64ByLocal(String imgFile) {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
 
	
	
	/**
	 * 在线图片转换成base64字符串
	 * 
	 * @param imgURL	图片线上路径
	 * @return
	 *
	 */
	public static String ImageToBase64ByOnline(String imgURL) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		try {
			// 创建URL
			URL url = new URL(imgURL);
			byte[] by = new byte[1024];
			// 创建链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			InputStream is = conn.getInputStream();
			// 将内容读取内存中
			int len = -1;
			while ((len = is.read(by)) != -1) {
				data.write(by, 0, len);
			}
			// 关闭流
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data.toByteArray());
	}
	
	
	/**
	 * base64字符串转换成图片
	 *   data:image/jpeg;base64,  截取后的编码
	 * @param imgStr		base64字符串
	 * @param imgFilePath	图片存放路径
	 * @return
	 *
	 */
	public static boolean Base64ToImage(String imgStr,String imgFilePath) {
		// 图像数据为空
		if (imgStr == null || imgStr.isEmpty())  {
			return false;
		}

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				// 调整异常数据
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
 
			return true;
		} catch (Exception e) {
			return false;
		}
	}
 
	public static String bufferedImageToBase64(BufferedImage bufferedImage) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
        String base64Img = encoder.encode(outputStream.toByteArray());
        return base64Img;
	}
	
	public static byte[] bufferedImageToByte(BufferedImage bufferedImage) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return outputStream.toByteArray();
	}

	public static void  main(String[] args) {
		System.out.println(Base64Utils.ImageToBase64ByLocal("C:\\Users\\lvzonggang\\Pictures\\Camera Roll\\1.jpg"));

	}
}

