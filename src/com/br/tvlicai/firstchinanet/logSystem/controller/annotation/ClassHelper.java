/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.logSystem.controller.annotation;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Add the description here.
 * Create on 2016/8/24
 *
 * @author xuhai
 * @version 0.0.0
 */
public class ClassHelper {
    static final Logger _LOG = LoggerFactory.getLogger(ClassHelper.class);
    /**
     *
     * <p>
     * 获取方法参数名称
     * </p>
     *
     * @param cm
     * @return
     */
    protected static String[] getMethodParamNames(CtMethod cm) {
        CtClass cc = cm.getDeclaringClass();
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
                .getAttribute(LocalVariableAttribute.tag);
        if (attr != null) {
            String[] paramNames = null;
            try {
                paramNames = new String[cm.getParameterTypes().length];
                int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
                for (int i = 0; i < paramNames.length; i++) {
                    paramNames[i] = attr.variableName(i + pos);
                }
                return paramNames;
            } catch (NotFoundException e) {
                _LOG.error("异常信息", e);
            }
        }
        return null;
    }


    public static String[] getMethodParamNames(Class<?> clazz, String method, Class<?>... paramTypes) {

        ClassPool pool = ClassPool.getDefault();

        String className = clazz.getName();

        className = className.substring(0, className.indexOf("$"));

        CtClass cc;
        CtMethod cm = null;
        try{
            if(pool.find(className) == null){
                Class zz = Class.forName(className);
                pool.appendClassPath(new ClassClassPath(zz));
            }
            cc = pool.get(className);

            String[] paramTypeNames = new String[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i++)
                paramTypeNames[i] = paramTypes[i].getName();

            cm = cc.getDeclaredMethod(method, pool.get(paramTypeNames));
        }
        catch (Exception e){
            _LOG.error("异常信息", e);
        }

        return getMethodParamNames(cm);
    }
}
