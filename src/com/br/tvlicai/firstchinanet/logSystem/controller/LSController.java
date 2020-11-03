/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.logSystem.controller;

import java.lang.annotation.*;

/**
 * Add the description here.
 * Create on 2016/6/20
 *
 * @author xuhai
 * @version 0.0.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LSController {
    String value() default "";
}
