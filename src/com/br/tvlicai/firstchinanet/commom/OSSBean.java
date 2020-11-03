package com.br.tvlicai.firstchinanet.commom;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.CannedAccessControlList;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * Created by Administrator on 2014/8/19.
 */
public class OSSBean {

    static final Logger _LOG = LoggerFactory.getLogger(OSSBean.class);
    private static String accessKeyId = null;
    private static String accessKeySecret = null;
    private static OSSClient client = null;
    private static String bucketName = null;
    private static String endpoint = null;
    private static String img_domain = null;

    static {
        accessKeyId = PropertiesUtils.get("accessKeyId").toString();
        accessKeySecret = PropertiesUtils.get("accessKeySecret").toString();
        endpoint = PropertiesUtils.get("endpoint").toString();
        bucketName = PropertiesUtils.get("bucketName").toString();
        img_domain = PropertiesUtils.get("img-domain").toString();
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        if (!client.doesBucketExist(bucketName)) {
            client.createBucket(bucketName);
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

        }
    }

    /**
     * 图片保存方法
     * @param key
     * @param content
     * @param fileLength
     * @return
     * @throws java.io.FileNotFoundException
     */
    public static String putObject(String key, InputStream content, long fileLength) throws FileNotFoundException {

        try {
            _LOG.info("put object : "+key +" file length "+ fileLength);
            String fileType = key.substring(key.lastIndexOf(".") + 1);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(fileLength);
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(OssBeanUtil.contentType(fileType));
            metadata.setContentDisposition("inline;filename=" + key);
            client.putObject(bucketName, key, content, metadata);
            return img_domain + key;
        } catch (OSSException e) {
            _LOG.error("oss put image object error "+e);
            return null;
        } catch (ClientException e) {
            _LOG.error("oss put image object error "+e);
            return null;
        }
    }

    /**
     * 文件保存方法
     * @param key
     * @param content
     * @param fileLength
     * @return
     */
    public static String pubFileObject(String key, InputStream content, long fileLength){
        String fileType = key.substring(key.lastIndexOf(".") + 1);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileLength);
        metadata.setCacheControl("no-cache");
        metadata.setHeader("Pragma", "no-cache");
        metadata.setContentEncoding("utf-8");
        metadata.setContentType(OssBeanUtil.contentType(fileType));
        metadata.setContentDisposition("inline;filename=" + key);
        PutObjectResult result = client.putObject(bucketName, key, content, metadata);
        String url = endpoint;
        url=url.replaceAll("http://","");
        url = "http://" + bucketName + "." + url + "/"+key;
        return url;
    }
}