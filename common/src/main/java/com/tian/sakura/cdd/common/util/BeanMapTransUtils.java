package com.tian.sakura.cdd.common.util;

import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class BeanMapTransUtils {

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static <T> Map<String, String> transBean2Map(T obj, Boolean camelStyle) {
        if(obj == null){
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (value == null) {
                        continue;
                    }
                    if (!camelStyle) {
                        key = humpToUnderline(key);
                    }
                    map.put(key, value.toString());
                }

            }
        } catch (Exception e) {
           e.printStackTrace();
        }

        return map;

    }

    /** 驼峰转 下划线
     *       userName  ---->  user_name
     *       user_name  ---->  user_name
     * @param humpName  驼峰命名
     * @return  带下滑线的String
     */
    public static String humpToUnderline(String humpName) {
        //截取下划线分成数组，
        char[] charArray = humpName.toCharArray();
        StringBuffer buffer = new StringBuffer();
        //处理字符串
        for (int i = 0,l=charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_").append(charArray[i] += 32);
            }else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }
}
