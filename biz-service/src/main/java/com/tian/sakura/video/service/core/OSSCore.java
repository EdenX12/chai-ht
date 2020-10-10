package com.tian.sakura.video.service.core;

import com.aliyun.oss.OSSClient;
import com.tian.sakura.cdd.common.constants.SrvConstants;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.OSSUtil;
import com.tian.sakura.video.service.auth.ParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OSSCore {

    @Autowired
    private ParamService paramService;

    public String upload(MultipartFile file) {
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = paramService.getValueByKey(SrvConstants.ALI_ACCESS_KEY_ID);
        String accessKeySecret = paramService.getValueByKey(SrvConstants.ALI_ACCESS_KEY_SECRET);
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        return OSSUtil.upload(file, paramService.getValueByKey(SrvConstants.OSS_BUCKET), ossClient);
    }

    public void delete(String imageName) {
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        if (StringUtils.isEmpty(imageName) || !imageName.contains(endpoint)) {
            throw new BizRuntimeException(RespCodeEnum.PARAM_DEFECT);
        }
        String[] strings = imageName.split(endpoint + "/");
        String key = strings[1];
        String accessKeyId = paramService.getValueByKey(SrvConstants.ALI_ACCESS_KEY_ID);
        String accessKeySecret = paramService.getValueByKey(SrvConstants.ALI_ACCESS_KEY_SECRET);
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSUtil.delete(key, paramService.getValueByKey(SrvConstants.OSS_BUCKET), ossClient);
    }
}
