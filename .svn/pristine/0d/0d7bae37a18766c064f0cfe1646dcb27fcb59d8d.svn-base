package com.tian.sakura.cdd.srv.service.login;

import com.tian.sakura.cdd.common.dict.ERecordStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.login.CustAuthToken;
import com.tian.sakura.cdd.db.manage.login.CustAuthTokenManage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token
 *
 * @author lvzonggang
 */
@Service
public class CustTokenService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String API_SECRET = "auct1qaz2wsx";
    private final static int EXRA = 5 * 24 * 60 * 60;
    private final static String PARAM_TOKEN = "token";

    @Autowired
    private CustAuthTokenManage authTokenManage;

    Map<String, String> tokenMap = new HashMap<>();

    public String geneToken(String bizCode) {
        String token = doGenToken(bizCode);

        // 保存令牌信息
        CustAuthToken authToken = authTokenManage.selectByPrimary(bizCode);
        if (authToken == null) {
            authToken = new CustAuthToken();
            authToken.setCustNo(bizCode);
            authToken.setStartTime(System.currentTimeMillis() / 1000);
            authToken.setToken(token);
            authTokenManage.insert(authToken);
        } else {
            authToken.setToken(token);
            authToken.setStartTime(System.currentTimeMillis() / 1000);
            authToken.setStatus(ERecordStatus.VALID.getCode());
            authTokenManage.updateByPrimaryKeySelective(authToken);
        }


        //tokenMap.put(bizCode, token);
        logger.debug("token.>>" + token);
        return token;
    }

    //该方法使用HS256算法和Secret:生成signKey
    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(API_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    private String doGenToken(String bizCode) {
        //生成token令牌 T
        Key key = getKeyInstance();
        String token = Jwts.builder()
                .setSubject(bizCode)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return token;
    }

    private String parseToken(String token) {
        Key key = getKeyInstance();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        String bizCode = claims.getSubject();
        return bizCode;
    }

    public Map<String, Object> validateToken(String token) {
        Map<String, Object> result = new HashMap<>();

        //解析令牌
        try {
            String bizCode = parseToken(token);
            result.put("bizCode", bizCode);

            CustAuthToken authToken = authTokenManage.selectByPrimary(bizCode);
            if (authToken == null) {
                //result.put(PARAM_TOKEN, false);
                throw new BizRuntimeException(RespCodeEnum.FAIL_TOKEN_CHECK);
            } else {
                long now = System.currentTimeMillis() / 1000;
                long start = authToken.getStartTime();
                logger.info("客户[{}]令牌的创建时间{}秒，当前时间{}秒, 时间差{}秒", bizCode, start, now, now - start);
                if (now - start > EXRA) {
                    result.put(PARAM_TOKEN, false);
                } else {
                    result.put(PARAM_TOKEN, true);
                }

            }
        } catch (SignatureException e) {
            logger.error(e.getMessage(), e);
            //result.put(PARAM_TOKEN, false);
            throw new BizRuntimeException(RespCodeEnum.FAIL_TOKEN_CHECK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //result.put(PARAM_TOKEN, false);
            throw new BizRuntimeException(RespCodeEnum.FAIL_TOKEN_CHECK);
        }
        return result;
    }

    public String refreshToken(String token) {
        try {
            //解析令牌
            String bizCode = parseToken(token);
            //检查该客户是否生成过令牌
            CustAuthToken authToken = authTokenManage.selectByPrimary(bizCode);
            if (authToken == null) {
                throw new BizRuntimeException(RespCodeEnum.FAIL_TOKEN_CHECK);
            } else {
                logger.info("客户[{}]刷新token", bizCode);
                return geneToken(bizCode);
            }
        } catch (SignatureException e) {
            logger.error(e.getMessage(), e);
            //result.put(PARAM_TOKEN, false);
            throw new BizRuntimeException(RespCodeEnum.FAIL_TOKEN_CHECK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //result.put(PARAM_TOKEN, false);
            throw new BizRuntimeException(RespCodeEnum.FAIL_TOKEN_CHECK);
        }
    }

    public void removeToken(String bizCode) {
        logger.info("客户【{}】正在退出...", bizCode);
        tokenMap.remove(bizCode);
        authTokenManage.deleteByPrimaryKey(bizCode);
        logger.info("客户【{}】移除token...", bizCode);
    }
}
