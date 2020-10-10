package com.tian.sakura.cdd.srv.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author lvzonggang
 */

@Service("inviteCodeRedisGenerator")
public class InviteCodeRedisGenerator implements InviteCodeGenerator{
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public String next() {
        return "";
    }
}
