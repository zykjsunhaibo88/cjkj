package com.neusoft.unieap.action;

import com.neusoft.unieap.config.EAPConfigHelper;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;

public class EAPRequestUtils extends RequestUtils {

   public static void selectModule(HttpServletRequest request, ServletContext context) {
      String prefix = getModuleName(request, context);
      selectModule(prefix, request, context);
   }

   public static String getModuleName(HttpServletRequest request, ServletContext context) {
      String matchPath = (String)request.getAttribute("javax.servlet.include.servlet_path");
      if(matchPath == null) {
         matchPath = request.getServletPath();
      }

      return getModuleName(matchPath, request, context);
   }

   public static String getModuleName(String matchPath, HttpServletRequest request, ServletContext context) {
      if(log.isDebugEnabled()) {
         log.debug("Get module name for path " + matchPath);
      }

      String path = matchPath;
      if(matchPath.startsWith(EAPConfigHelper.getContextPath(request))) {
         matchPath = matchPath.substring(EAPConfigHelper.getContextPath(request).length());
      }

      String prefix = "";
      String[] prefixes = getModulePrefixes(context);
      boolean lastSlash = false;

      int var8;
      while(prefix.equals("") && (var8 = matchPath.lastIndexOf("/")) > 0) {
         matchPath = matchPath.substring(0, var8);

         for(int i = 0; i < prefixes.length; ++i) {
            if(matchPath.equals(prefixes[i])) {
               prefix = prefixes[i];
               break;
            }
         }
      }

      if(prefix.equals("")) {
         prefix = EAPConfigHelper.getApplicationPrefix(request.getSession());
         if(matchPath.startsWith(EAPConfigHelper.getContextPath(request))) {
            path = matchPath.substring(EAPConfigHelper.getContextPath(request).length());
         }

         prefix = getProperPrefix(context, path, prefix);
         request.setAttribute("javax.servlet.include.servlet_path", prefix.concat(path));
      } else if(matchPath.startsWith(EAPConfigHelper.getContextPath(request))) {
         path = matchPath.substring(EAPConfigHelper.getContextPath(request).length());
         request.setAttribute("javax.servlet.include.servlet_path", path);
      }

      if(log.isDebugEnabled()) {
         log.debug("Module name found: " + (prefix.equals("")?"default":prefix));
      }

      return prefix;
   }

   protected static String getProperPrefix(ServletContext context, String fullPath, String prefix) {
      String path = fullPath;
      int slash = fullPath.lastIndexOf("/");
      int period = fullPath.lastIndexOf(".");
      if(period >= 0 && period > slash) {
         path = fullPath.substring(0, period);
      }

      ModuleConfig config = EAPConfigHelper.getModuleConfig(context, prefix);
    
     return "";
     
   }
}