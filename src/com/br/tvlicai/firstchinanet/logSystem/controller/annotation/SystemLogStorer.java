/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.logSystem.controller.annotation;

import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.br.tvlicai.firstchinanet.logSystem.controller.LSController;
import com.br.tvlicai.firstchinanet.logSystem.controller.LSMethodDesc;
import org.apache.log4j.chainsaw.Main;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Add the description here.
 * Create on 2016/6/21
 *
 * @author xuhai
 * @version 0.0.0
 */

public class SystemLogStorer extends Thread {

}
