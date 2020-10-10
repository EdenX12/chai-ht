package com.tian.sakura.cdd.common.util;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

/**
 * httpClient封装类
 *
 * @author lvzonggang
 */
public class HttpClientUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public  static String mockGet(String url) {
        HttpClient httpClient = new HttpClient();
        //超时配置
        HttpMethod getMethod = new GetMethod(url);
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (HttpURLConnection.HTTP_OK == statusCode) {
                return execute(getMethod);
            }
        } catch (Exception e) {
            throw new BizRuntimeException("-1", e.getMessage(),e);
        }

        return "";
    }

    public static String mockPost(String url, String parameter) {
        PostMethod postMethod = new PostMethod(url);
        RequestEntity requestEntity = null;
        if(parameter != null){
            try {
                requestEntity = new StringRequestEntity(parameter,"text/xml","UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage());
            }
            postMethod.setRequestEntity(requestEntity);
        }
        return executeMethod(postMethod);
    }

    public static String executeMethod(HttpMethod httpMethod) {
        HttpClient httpClient = new HttpClient();
        try {
            int statusCode = httpClient.executeMethod(httpMethod);
            logger.info("statusCode>>>>>>>>>>>>>>>" + statusCode);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                logger.error("失败返回码[" + statusCode + "]");
            } else {
                return execute(httpMethod);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return "";
    }

    private static String execute(HttpMethod httpMethod) throws Exception {
        StringBuffer sb = new StringBuffer();
        InputStream in = httpMethod.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        if (br != null) {
            br.close();
        }
        logger.info(">>>>>" + sb.toString());
        return sb.toString();
    }
}
