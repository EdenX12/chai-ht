package com.tian.sakura.video.service.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tian.sakura.cdd.common.util.HttpsUtil;
import com.tian.sakura.cdd.common.util.MD5;
import com.tian.sakura.cdd.db.domain.express.Express;
import com.tian.sakura.cdd.db.manage.express.ExpressManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpressService {

    @Autowired
    private ExpressManage expressManage;

    private static final String EXPRESS_KEY = "TRjsfeJT8443";

    private static final String AUTO_NUMBER = "http://www.kuaidi100.com/autonumber/auto";

    private static final String QUERY = "https://poll.kuaidi100.com/poll/query.do";

    private static final String CUSTOMER = "38E2F9F4D77D8A3B03C7562B06049549";

    public List<Express> listExpress(Express express) {
        return expressManage.queryExpressList(express);
    }

    public Object queryExpressByCode(String code) {
        code = code.trim();
        String autoNumberResp = HttpsUtil.doGet(AUTO_NUMBER + "?num=" + code + "&key=" + EXPRESS_KEY);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(JSON.parseArray(autoNumberResp).get(0)));
        Map<String, String> param = new HashMap<>();
        param.put("com", jsonObject.getString("comCode"));
        param.put("num", code);
        String sign = MD5.encode(JSONObject.toJSONString(param) + EXPRESS_KEY + CUSTOMER);
        Map<String, Object> queryReq = new HashMap<>();
        queryReq.put("param", JSONObject.toJSONString(param));
        queryReq.put("sign", sign);
        queryReq.put("customer", CUSTOMER);
        Map<String, String> queryResp = HttpsUtil.doPostForMap(QUERY, queryReq);
        return queryResp.get("data");
    }
}
