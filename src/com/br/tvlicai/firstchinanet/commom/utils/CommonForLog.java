package com.br.tvlicai.firstchinanet.commom.utils;

import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.rmi.CORBA.Util;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zjj on 2016/7/15.
 */
public class CommonForLog {
    static final Logger _LOG = LoggerFactory.getLogger(CommonForLog.class);
    /**
     * 获取单号
     *
     * @param keyWord 单号标识
     * @return
     */
    public static String getOrderNum(String keyWord) {
        String str = "";
        if (!StringUtils.isEmpty(keyWord)) {
            String numlenth = "%07d";
            String dt = new SimpleDateFormat("yyMMdd").format(new Date());
            int num = (int) (Math.random() * 10000000);
            //左补零
            String number = String.format(numlenth, num);
            str = keyWord + dt + number;
        }
        return str;
    }

    public static void main(String args[]) throws Exception {
        String a = getOrderNum("AA");
        _LOG.debug(a);
    }
}
