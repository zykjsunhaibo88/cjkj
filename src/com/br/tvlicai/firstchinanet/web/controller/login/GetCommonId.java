package com.br.tvlicai.firstchinanet.web.controller.login;

import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by huhao on 2016/7/7.
 */
public class GetCommonId {
    static final Logger _LOG = LoggerFactory.getLogger(GetCommonId.class);
    private static String platformId;
    private static String mallId;
    private static String systemId;

    static Properties props = null;
    static {
        Resource resource = new ClassPathResource("/data.properties");
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
            platformId = props.getProperty("platformId").trim();
            mallId = props.getProperty("mallId").trim();
            systemId = props.getProperty("systemId").trim();
        } catch (IOException e) {
            _LOG.error("", e);
        }
    }
    public static String getPlatformId() {
        return platformId;
    }

    public static String getMallId() {
        return mallId;
    }

    public static String getSystemId() {
        return systemId;
    }
}
