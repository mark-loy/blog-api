package com.markloy.markblog.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.util.DesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class OSSProvider {

    @Value("${aliyun.endpoint}")
    private String endpoint;
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.filePatten}")
    private String filePatten;
    @Value("${aliyun.oss.expiration}")
    private Long expires;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return OSS外网路径
     */
    public Map<String, Object> fileUpload(MultipartFile file) throws Exception {
        // 创建解码器
        DesUtils des = new DesUtils("markLoyBlog");
        // 解码key、secret
        String key = des.decrypt(accessKeyId);
        String secret = des.decrypt(accessKeySecret);
        //构建OSS实列
        OSS ossClient = new OSSClientBuilder().build(endpoint, key, secret);
        //对文件名进行处理  \\代表转义
        String[] strings = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String suffix = strings[strings.length - 1];
        String fileName = UUID.randomUUID() + "." + suffix;
        // 构造上传文件对象
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePatten + fileName, file.getInputStream());
        // 上传文件
        ossClient.putObject(putObjectRequest);
        // 过期时间
        Date expiration = new Date(System.currentTimeMillis() + expires);
        // 获取返回的文件外网路径
        URL url = ossClient.generatePresignedUrl(bucketName, filePatten + fileName, expiration);
        // 关闭连接
        ossClient.shutdown();
        // 判断url是否为空
        if (url == null) {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_ERROR);
        }
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("url", url);
        resultMap.put("fileName", fileName);
        return resultMap;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    public void fileDelete(String fileName) throws Exception {
        // 创建解码器
        DesUtils des = new DesUtils("markLoyBlog");
        // 解码key、secret
        String key = des.decrypt(accessKeyId);
        String secret = des.decrypt(accessKeySecret);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, key, secret);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。
        // 如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, filePatten + fileName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
