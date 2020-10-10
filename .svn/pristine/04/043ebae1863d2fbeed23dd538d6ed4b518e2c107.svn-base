package com.tian.sakura.cdd.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * OSS工具类
 *
 * @author zhufeng
 * @since shop_v1.0
 */
@SuppressWarnings("deprecation")
@Component
@Slf4j
public class OSSUtil {

    /**
     * 上传文件到OSS
     *
     * @param file
     * @return 文件路径
     */
    public static String upload(MultipartFile file, String bucketName, OSSClient ossClient) {
        String key = file.getOriginalFilename();
        if (key != null) {
            key = key.replace('/', '_').replace('+', '_');
        }
        key = "image_" + System.currentTimeMillis() + "_" + key;
        File destFile = new File(key);
        destFile = new File(destFile.getAbsolutePath());
        try {
            file.transferTo(destFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpg");
        ossClient.putObject(bucketName, key, destFile, objectMetadata);
        ossClient.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
        URL url = ossClient.generatePresignedUrl(bucketName, key, DateUtils.addYears(new Date(), 10));
        ossClient.shutdown();
        destFile.delete();
        return "https://" + url.getHost() + url.getPath();
    }

    public static void delete(String imageName, String bucketName, OSSClient ossClient) {
        ossClient.deleteObject(bucketName, imageName);
        ossClient.shutdown();
    }
}
