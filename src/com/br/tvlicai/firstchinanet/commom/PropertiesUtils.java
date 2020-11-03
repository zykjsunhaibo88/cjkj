package com.br.tvlicai.firstchinanet.commom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2014/9/11.
 */
public class PropertiesUtils {
    static final Logger _LOG = LoggerFactory.getLogger(PropertiesUtils.class);
    static   Properties props = null;
    static {
        Resource resource = new ClassPathResource("/oss.properties");
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);

        } catch (IOException e) {
            _LOG.error("OSSBEAN�е������ļ������e�`", e);
        }
    }
    public static Object get(String name){
        return    props.get(name);
    }
    public static void set(String name,String value){
        props.setProperty(name,value);
    }


}
