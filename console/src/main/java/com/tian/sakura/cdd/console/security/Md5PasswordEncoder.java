package com.tian.sakura.cdd.console.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * MD5 加密
 *
 * @author lvzonggang
 */
public class Md5PasswordEncoder implements PasswordEncoder {

    private int iterateCount = 1;

    public Md5PasswordEncoder() {
        this(1);
    }

    public Md5PasswordEncoder(int iterateCount) {
        if (iterateCount < 1) {
            iterateCount = 1;
        }
        this.iterateCount = iterateCount;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return doEncoder(rawPassword.toString(),iterateCount);
    }

    private String doEncoder(String rawPassword, int iterateCount) {
        String encodedPassword = "";
//        int i = 1;
//        do {
//            encodedPassword = DigestUtils.md5Hex(rawPassword);
//            i ++ ;
//        } while( iterateCount > i);
//
//        return encodedPassword;
        return DigestUtils.md5Hex(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String encodeValue = encode(rawPassword);
        return StringUtils.equals(encodeValue, encodedPassword);
    }
}
