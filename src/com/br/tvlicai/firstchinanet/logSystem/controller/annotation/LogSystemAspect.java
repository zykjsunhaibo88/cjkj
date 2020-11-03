/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.logSystem.controller.annotation;

import com.br.tvlicai.firstchinanet.logSystem.controller.LSController;
import com.br.tvlicai.firstchinanet.logSystem.controller.LSMethodDesc;
import org.apache.log4j.chainsaw.Main;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Add the description here.
 * Create on 2016/6/20
 *
 * @author xuhai
 * @version 0.0.0
 */
public class LogSystemAspect {
}
