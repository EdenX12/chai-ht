package com.tian.sakura.cdd.srv.service.user;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.BizUtils;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.user.UserAddress;
import com.tian.sakura.cdd.db.manage.user.UserAddressManage;
import com.tian.sakura.cdd.srv.web.user.dto.UserAddressRspBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户地址API的服务
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class UserAddressApiService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserAddressManage userAddressManage;

    public List<UserAddressRspBody> selectByUserId(String userId) {
        List<UserAddress> addressList = userAddressManage.selectByUserId(userId);
        List<UserAddressRspBody> bodyList = new ArrayList<>();

        addressList.forEach(item -> {
            UserAddressRspBody body = new UserAddressRspBody();
            BeanUtils.copyProperties(item, body);
            body.setId(item.getId());
            body.setMobPhone(BizUtils.desensiteMobile(item.getMobPhone()));
            body.setDefaultFlag(checkDefault(item.getIsDefault()));
            bodyList.add(body);
        });
        return bodyList;
    }

    public void addAddress(UserAddress userAddress) {
        if (StringUtils.isEmpty(userAddress.getId())) {
            userAddress.setId(IdGenUtil.uuid());
        }
        //如果本次新增的地址为默认地址，那么更新数据库其他地址为非默认
        if (checkDefault(userAddress.getIsDefault())) {
            userAddressManage.updateAddressNoDefaultByUserId(userAddress.getUserId());
        }
        userAddressManage.insert(userAddress);

        logger.info("用户[{}]新增地址[{}],是否默认地址[{}]",
                userAddress.getUserId(), userAddress.getAreaInfo(), userAddress.getIsDefault());
    }

    public void updateAddress(UserAddress userAddress) {
        if (StringUtils.isEmpty(userAddress.getId())) {
            throw  new BizRuntimeException(RespCodeEnum.NO_BIZ_ID);
        }
        //如果本次新增的地址为默认地址，那么更新数据库其他地址为非默认
        if (userAddress.getMobPhone().contains("*")) {
            //如果前段传的手机号码包含*， 设置为空
            userAddress.setMobPhone(null);
        }
        userAddressManage.updateByPrimaryKeySelective(userAddress);

        logger.info("用户[{}]修改地址[{}]完成,是否默认地址[{}]",
                userAddress.getUserId(), userAddress.getId(), userAddress.getIsDefault());
    }

    public void deleteById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw  new BizRuntimeException(RespCodeEnum.NO_BIZ_ID);
        }
        userAddressManage.deleteByPrimaryKey(id);
    }

    private boolean checkDefault(String defaultCode) {
        return StringUtils.equals(UserAddressManage.DEFALUT_STATUS, defaultCode);
    }
}
