package com.br.tvlicai.firstchinanet.web.controller.login;

import com.br.tvlicai.firstchinanet.business.common.Util.CheckIPAddress;
import org.apache.http.util.EntityUtils;
import com.br.tvlicai.firstchinanet.business.common.Util.Util;
import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.br.tvlicai.firstchinanet.logSystem.controller.LSController;
import com.google.common.base.Joiner;
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.config.EAPConfigHelper;
import com.neusoft.unieap.util.DBAccessHelper;
import com.br.tvlicai.firstchinanet.CommonUtil;
import com.br.tvlicai.firstchinanet.app.controller.base.BaseController;
import com.br.tvlicai.firstchinanet.app.controller.menu.BrtCmnMenuMall;
import com.br.tvlicai.firstchinanet.app.controller.menu.MenuInfo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.alibaba.fastjson.JSON;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static org.apache.http.protocol.HTTP.USER_AGENT;
/**
 * Created by wzy on 2016/8/2.
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    static final Logger _LOG = LoggerFactory.getLogger(LoginController.class);


    // 1. /login 登录页面的常规显示
    // 2. /login?error 登录验证失败的展示
    // 3. /login?logout 注销登录的处理
    /**
     * 登录展示页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/sysLogin")
    public ModelAndView login(ModelAndView modelAndView,HttpServletRequest request, HttpSession session) {
        getPlatformIdAndMallIdByRequest(request, session);
        try {
            modelAndView.setViewName("sysLogin");
        } catch (Exception e) {
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginError 异常请联系管理员");
            _LOG.error("异常信息", e);
        }
        return modelAndView;
    }

    /**
     * 登录失败页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/loginError")
    public ModelAndView loginError(ModelAndView modelAndView) {
        try {
            modelAndView.addObject("false1", "用户名或密码填写错误！");
            modelAndView.setViewName("sysLogin");
        } catch (Exception e) {
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginError 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：wzy");
        }
        return modelAndView;
    }

    /**
     * 登录失败页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/loginFail")
    public ModelAndView loginFail(ModelAndView modelAndView) {
        try {
            modelAndView.addObject("false1", "您的账号已被禁用，无法登录！");
            modelAndView.setViewName("sysLogin");
        } catch (Exception e) {
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginError 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：wzy");
        }
        return modelAndView;
    }

    /**
     * 登录
     *
     * @param session
     * @param modelAndView
     * @return
     */
    @RequestMapping("/loginMain")
    @LSController("登录的主要处理方法")
    public ModelAndView loginMain(HttpSession session, ModelAndView modelAndView, HttpServletRequest request) {
        User user = getUser(session);
        BruAuthentication b = getBruAuthentication(user);

        String sessionPlatformId = (String) session.getAttribute(Const.USERINFO_PLATFORM_ID);
        String sessionMallId = (String) session.getAttribute(Const.USERINFO_MALL_ID);

        boolean login_flag = false;
        DBPersistenceManager pm=null;
        List<BrtCmnMallInfoUIBean> mallList = new ArrayList<BrtCmnMallInfoUIBean>();
        try {

        	pm=DBAccessHelper.getPMByName(Const.DataSourceName);
	       
            DataSet dsuserMall=(DataSet)pm.executeQuery("select  "
            		+ "  SHIP_ID, PLATFORM_ID, MALL_ID, USER_AID, APPROVE_STS_CD, APPROVE_USER_ID, APPROVE_DATE, " + 
            		"    CREATED_USER_ID, CREATED_DATE, UPDATED_USER_ID, UPDATED_DATE, DELETE_TYPE  from brt_cmn_mall_user_ship where USER_AID  = '"+b.getUserAid()+"' AND DELETE_TYPE = 1 ");
	        while(dsuserMall!=null&&dsuserMall.next()){		
	        	BrtCmnMallInfoUIBean bcmus=new BrtCmnMallInfoUIBean();
	        	bcmus.setPlatformId(dsuserMall.getString("PLATFORM_ID"));
	        	bcmus.setMallId(dsuserMall.getString("MALL_ID"));
	        	DataSet dscurrentMallBean = (DataSet)pm.executeQuery(" select MALL_UUID, MALL_ID, PLATFORM_ID, MALL_NAME, MALL_STS, MALL_LEVEL, "
	        			+ "  CREATED_USER_ID, RFID_MODE,LOCK_PUBLICITY, " + 
	        			"    CREATED_DATE,UPDATED_USER_ID, UPDATED_DATE, DELETE_TYPE " + 
	        			"    from brt_cmn_mall_info where  MALL_ID ='"+dsuserMall.getString("MALL_ID")+"'  AND PLATFORM_ID ='"+dsuserMall.getString("PLATFORM_ID")+"'"); 
	        	
	        	  if (dscurrentMallBean != null && dscurrentMallBean.next()){
	        		  if ("0".equals(dscurrentMallBean.getString("MALL_LEVEL"))){
		                mallList.add(bcmus);
	        		  }
		          }
	        }
	        login_flag = checkLogin(b.getUserAid(), b.getUserType(), sessionPlatformId, sessionMallId);
	
	        if (!login_flag) {
	            for (int i = 0; i < mallList.size(); i++) {
	                BrtCmnMallInfoUIBean currentMallInfo = mallList.get(i);
	                login_flag = checkLogin(b.getUserAid(), b.getUserType(), currentMallInfo.getPlatformId(), currentMallInfo.getMallId());
	                if (login_flag) {
	                    sessionPlatformId = currentMallInfo.getPlatformId();
	                    sessionMallId = currentMallInfo.getMallId();
	                    break;
	                }
	            }
	        }
	
	        if (!login_flag) {
	            session.invalidate();
	            session = request.getSession(true);
	            session.setAttribute(Const.USERINFO_PLATFORM_ID, sessionPlatformId);
	            session.setAttribute(Const.USERINFO_MALL_ID, sessionMallId);
	            modelAndView.addObject("false1", "您的账号已被禁用，无法登录！");
	            modelAndView.setViewName("sysLogin");
	            return modelAndView;
	        }
	
	        session.setAttribute(Const.USERINFO_PLATFORM_ID, sessionPlatformId);
	        session.setAttribute(Const.USERINFO_MALL_ID, sessionMallId);
	
        }catch (Exception e) {
            _LOG.error("异常信息：" + e.toString());
        }finally{
        	if(pm!=null){
        		pm.close();
        	}
        }
        session.setAttribute(Const.USERINFO_PLATFORM_ID, mallList.get(0).getPlatformId());
        session.setAttribute(Const.USERINFO_MALL_ID, mallList.get(0).getMallId());
        return new ModelAndView(new RedirectView("/chooseLogin"));

    }

    @RequestMapping("/selectedMallAndPlatform")
    @LSController("选择商城检查方法")
    @ResponseBody
    public Map<String, Object> selectedMallAndPlatform(HttpSession session, HttpServletRequest request, String platformId, String mallId) {
        Map<String, Object> rtn = new HashMap<String, Object>();
        boolean login_flag = false;
        User user = getUser(session);
        BruAuthentication b = getBruAuthentication(user);
        login_flag = checkLogin(b.getUserAid(), b.getUserType(), platformId, mallId);
        rtn.put("login_flag", login_flag);

        if (!login_flag) {
            session.invalidate();
            session = request.getSession(true);
        }
        session.setAttribute(Const.USERINFO_PLATFORM_ID, platformId);
        session.setAttribute(Const.USERINFO_MALL_ID, mallId);

        return rtn;
    }

    /**
     * 选择商城后登录方法
     *
     * @param session
     * @param modelAndView
     * @return
     */
    @RequestMapping("/chooseLogin")
    @LSController("选择商城后登录的主要处理方法")
    public ModelAndView chooseLogin(HttpSession session, ModelAndView modelAndView, HttpServletResponse response) {
        try {
            //系统ID
            String systemId = GetCommonId.getSystemId();
            User user = getUser(session);
            BruAuthentication b = getBruAuthentication(user);
            String platformId = (String) session.getAttribute(Const.USERINFO_PLATFORM_ID);
            String mallId = (String) session.getAttribute(Const.USERINFO_MALL_ID);

            String userGovStatus = getUserGovStatus(b.getUserAid(), mallId, platformId);//用户政府审核状态
            String userMallStatus = getUserMallStatus(b.getUserAid(), mallId, platformId);//用户平台审核状态

            session.setAttribute(Const.MALL_DISP_NAME,"沈阳");//商城地址缩略

            Map<String, Object> counts =countMsgSts(b.getUserAid());
            session.setAttribute(Const.USERINFO_USER_MSG_NUM, counts.get("allCount"));
            if (Util.isCon(b)) {
                session.setAttribute(Const.USER_INFO, b);
                session.setAttribute(Const.USERINFO_USER_ID, b.getUserId());//用户ID
                session.setAttribute(Const.USERINFO_USER_AIDOLD, b.getUserAid());//用户旧AID
                session.setAttribute(Const.USERINFO_USER_AID, b.getUserAid());//用户AID
                session.setAttribute(Const.USERINFO_USER_TYPE, b.getUserType());//用户类型
                session.setAttribute(Const.USERINFO_USER_GOV_STS_CD, userGovStatus);//用户政府审核状态
                session.setAttribute(Const.USERINFO_USER_MALL_STS_CD, userMallStatus);//用户平台审核状态
                // 设置所登录用户的菜单信息到SESSION中

                // 从session中获取登陆用户的类型
                String userType = (String) session.getAttribute(Const.USERINFO_USER_TYPE);
                // 从session中获取登陆用户所属的群体用户的类型
                String groupType = (String) session.getAttribute(Const.SESSION_SEL_GROUP_TYPE);
                List<BruRoleDefinition> lstRoleInfo = queryRoleListByUserIdAndGroupType(b.getUserId(), userType, groupType);
                List<String> lstRoleTypes = new ArrayList<>();
                if (lstRoleInfo != null && lstRoleInfo.size() > 0) {
                    for (BruRoleDefinition bean : lstRoleInfo) {
                        lstRoleTypes.add(bean.getRoleId());
                    }
                }else {
                	
                }
                char approveFlag= '0';
                List<MenuInfo> menuTopInfo = getLoginUserTopMenuInfo(platformId, mallId, systemId, lstRoleTypes, approveFlag);
                List<MenuInfo> menuLeftInfo =getLoginUserLeftMenuInfo(platformId, mallId, systemId, lstRoleTypes, approveFlag);

                if (menuTopInfo != null && !menuTopInfo.isEmpty()) {
                    session.setAttribute(Const.SESSION_BUSINESS_MENU_TOP_INFO, menuTopInfo);
                }
                if (menuLeftInfo != null && !menuLeftInfo.isEmpty()) {
                    session.setAttribute(Const.SESSION_BUSINESS_MENU_LEFT_INFO, menuLeftInfo);
                }

                if(userType.equals("E01"))
                {
                	 DBPersistenceManager pm=null;
                     try {
                     	 pm=DBAccessHelper.getPMByName(Const.DataSourceName);
                         DataSet ds=(DataSet)pm.executeQuery("select group_id from bru_authentication where USER_AID  = '"+b.getUserAid()+"' ");
                         if(ds!=null&&ds.next()){
                         		session.setAttribute(Const.USERINFO_USER_ORGID, ds.getString("group_id")); 
                         }
                        }catch (Exception e) {
                             _LOG.error("异常信息：" + e.toString());
                        }finally{
                         	if(pm!=null){
                         		pm.close();
                         	}
                        }
                  
                }
                
            
                //token中，保留对应的群体aid
                
                EAPConfigHelper.setApplicationName(session, b.getUserAid());
                return new ModelAndView(new RedirectView("/userCenter"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginMain 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：wzy");
        }
        return modelAndView;
    }

    // 检查是否可以登录 true:可以登录
    private boolean checkLogin(String userAid, String userType, String platformId, String mallId) {
        boolean loginFlag = true;
        String userGovStatus = getUserGovStatus(userAid, mallId, platformId);//用户政府审核状态
        String userMallStatus = getUserMallStatus(userAid, mallId, platformId);//用户平台审核状态

        if (userGovStatus.equals(Const.USER_APPROVE_STS_9) || userMallStatus.equals(Const.USER_APPROVE_STS_9)) {
            loginFlag = false;
        }

        return loginFlag;
    }


    private User getUser(HttpSession session) {
        User user = null;
        Object ssc = session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (ssc instanceof SecurityContext) {
            SecurityContext sc = (SecurityContext) ssc;
            Object auo = sc.getAuthentication().getPrincipal();
            if (auo instanceof User) {
                user = (User) auo;
            }
            else{
                _LOG.error("SPRING_SECURITY_CONTEXT：auo异常，" + auo.toString() +" Author：xh");
            }
        }
        else{
            _LOG.error("SPRING_SECURITY_CONTEXT：ssc异常，"+ ssc.toString() +" Author：xh");
        }
        return user;
    }

    private BruAuthentication getBruAuthentication(User u) {
        BruAuthentication b = null;
        if (u != null) {
          
            List<BruAuthentication> aList = new ArrayList();
            
            DBPersistenceManager pm=null;
            try {

            	pm=DBAccessHelper.getPMByName(Const.DataSourceName);
    	        DataSet ds=(DataSet)pm.executeQuery("select     USER_AID, USER_ID, password, user_type, last_login_date, USER_STS_CD, CREATED_USER_ID, " + 
    	        		"    CREATED_DATE, UPDATED_USER_ID, UPDATED_DATE, DELETE_TYPE  from BRU_AUTHENTICATION  where 1=1  AND binary USER_ID = '"+u.getUsername()+"' ");     
    	        if (ds!=null&&ds.next()) {
                    b = new BruAuthentication();
                    b.setUserAid(ds.getString("USER_AID"));
                    b.setUserId(ds.getString("USER_ID"));
                    b.setPassword(ds.getString("password"));
                    b.setUserType(ds.getString("user_type"));
                    b.setLastLoginDate(ds.getDate("last_login_date"));
                    b.setUserStsCd(ds.getString("USER_STS_CD"));
                    b.setDeleteType(ds.getString("DELETE_TYPE"));
                }
            }catch (Exception e) {
            	_LOG.error("LoginController1：异常 " + u.getUsername() + "认证信息获取失败 Author：xh");
            }finally{
            	if(pm!=null){
            		pm.close();
            	}
            }
           
        }
        else{
            _LOG.error("LoginController2：异常 user为空， Author：xh");
        }
        return b;
    }

    @RequestMapping("/getVcode")
    @ResponseBody
    public boolean getVcode(HttpServletRequest request, HttpSession session, String vcode,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        boolean flag = false;
        try {
            if(!request.getParameterMap().containsKey("vcode")){
                flag = true;
                return flag;
            }
            if(null == session.getAttribute("valcode")){
                flag = true;
                return flag;
            }else {
                String valcode = session.getAttribute("valcode").toString();
                if (valcode.equals(vcode)) {
                    //session.removeAttribute("valcode");
                    flag = true;
                }else{
                    flag = false;
                }
            }
            // flag = userProcessInterface.checkExist(Const.USER_CHECK_TYPE_EMAIL, email);
        } catch (Exception e) {
            _LOG.error("类：registerController; 方法：getVcode; 异常邮箱重复验证失败; 异常：" + e.toString() + "Author:Memory");
        }
        return flag;
    }


    @RequestMapping("/vcode")
    public void vcode(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 告知浏览当作图片处理
        response.setContentType("image/jpeg");
        // 告诉浏览器不缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        // 产生由4位数字构成的验证码
        int length = 4;
        String valcode = "";
        Random rd = new Random();
        for (int i = 0; i < length; i++)
            valcode += rd.nextInt(10);
        // 把产生的验证码存入到Session中
        session.setAttribute("valcode", valcode);
        // 产生图片
        int width = 80;
        int height = 25;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取一个Graphics
        Graphics g = img.getGraphics();
        // 填充背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 填充干扰线50
        for (int i = 0; i < 50; i++) {
            g.setColor(new Color(rd.nextInt(120) + 135, rd.nextInt(120) + 135, rd.nextInt(120) + 135));
            g.drawLine(rd.nextInt(width), rd.nextInt(height), rd.nextInt(width), rd.nextInt(height));
        }
        // 绘制边框
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width - 1, height - 1);
        // 绘制验证码
        Font[] fonts = {new Font("隶书", Font.BOLD, 18), new Font("楷体", Font.BOLD, 18), new Font("宋体", Font.BOLD, 18), new Font("幼圆", Font.BOLD, 18)};
        for (int i = 0; i < length; i++) {
            g.setColor(new Color(rd.nextInt(150), rd.nextInt(150), rd.nextInt(150)));
            g.setFont(fonts[rd.nextInt(fonts.length)]);
            g.drawString(valcode.charAt(i) + "", width / valcode.length() * i + 2, 18);
        }
        // 输出图像
        g.dispose();
        ImageIO.write(img, "jpeg", response.getOutputStream());
        response.getOutputStream().close();
    }
    /**
     * 根据request中获取商城和平台id
     * 匹配顺序session、Cookie、ip地址、配置文件。
     * 如果session中有有平台和商城id 不进行修改，否则修改session中的平台商城id
     * 如果session中已有平台ID和商城ID，则停止后续匹配。
     * 如果Cookie中已有平台ID和商城ID，则停止后续匹配。
     * 如果ip地址配置到平台ID和商城ID，则停止后续匹配。
     * 如果上述都配置不到平台ID和商城ID，则返回配置文件中商城和平台id。
     */
    public BrtCmnPMBean getPlatformIdAndMallIdByRequest(HttpServletRequest request, HttpSession session){

         BrtCmnPMBean brtCmnPMBean = new BrtCmnPMBean();

        boolean isFounded = false;
        boolean isNeedBounding = false;

        String platformId = (String) session.getAttribute(Const.USERINFO_PLATFORM_ID);
        String mallId = (String) session.getAttribute(Const.USERINFO_MALL_ID);

        if(platformId != null && mallId != null){
            brtCmnPMBean.setPlatformId(platformId);
            brtCmnPMBean.setMallId(mallId);
            isFounded = true;
        } else {

            isNeedBounding = true;

            //根据cookieName取cookieValue
            String cookiePlatformId = null;
            String cookieMallId = null;
            //如果cookieValue为空,返回,
            if (cookiePlatformId != null && cookieMallId != null) {
                brtCmnPMBean.setPlatformId(cookiePlatformId);
                brtCmnPMBean.setMallId(cookieMallId);
                isFounded = true;
            }
            if(!isFounded){
                try{
                    String ip = getIpAddress(request,  session);
                    try {
                        if (CheckIPAddress.isLocalAddress(ip))
                        {								
                            ip=CheckIPAddress.getRemoteAddress().getCip();//获取本地局域网IP所在的外网IP
                        }
                    } catch (Exception e) {
                        throw new IOException(e.getMessage());
                    }
                    String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    HttpGet httpget = new HttpGet(url);
                    httpget.addHeader("User-Agent", USER_AGENT);
                    CloseableHttpResponse response = httpclient.execute(httpget);
                    String jsonStr = "";
                    try {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            jsonStr = EntityUtils.toString(entity, "UTF-8");
                            System.out.println("jsonStr:"+jsonStr);
                            //jsonStr="{\"code\":0,\"data\":{\"country\":\"\\u4e2d\\u56fd\",\"country_id\":\"CN\",\"area\":\"\\u4e1c\\u5317\",\"area_id\":\"200000\",\"region\":\"\\u8fbd\\u5b81\\u7701\",\"region_id\":\"210000\",\"city\":\"\\u6c88\\u9633\\u5e02\",\"city_id\":\"210100\",\"county\":\"\",\"county_id\":\"-1\",\"isp\":\"\\u8054\\u901a\",\"isp_id\":\"100026\",\"ip\":\"119.119.67.139\"}}";
                            EntityUtils.consume(entity);

                            boolean isJsonPaseOK = false;
                            IpResponseBody ipResponseBody = null;
                            if(!CommonUtil.isNull(jsonStr)){
                                try{
                                    ipResponseBody = JSON.parseObject(jsonStr, IpResponseBody.class);
                                }finally {
                                    if(ipResponseBody != null){
                                        isJsonPaseOK = true;
                                    }
                                }
                            }

                        }
                    }
                    catch (Exception e){
                        //_LOG.error("异常信息", e);
                        //_LOG.error("Get user position by ip failed, the response of ip.taobao.com is:"+jsonStr.toString());
                    	brtCmnPMBean.setPlatformId(Const.PlatformId);
                        brtCmnPMBean.setMallId(Const.CWS_MID);
                    }
                    finally {
                        response.close();
                    }
                }
                catch (IOException e){
                    _LOG.error("异常信息", e);
                }
            }
        }
        if(isFounded == false){
            brtCmnPMBean.setPlatformId(GetCommonId.getPlatformId());
            brtCmnPMBean.setMallId(GetCommonId.getMallId());
        }
        if(isNeedBounding == true){
            session.setAttribute(Const.USERINFO_PLATFORM_ID, brtCmnPMBean.getPlatformId());
            session.setAttribute(Const.USERINFO_MALL_ID, brtCmnPMBean.getMallId());
        }
        return brtCmnPMBean;
    }
    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public final String getIpAddress(HttpServletRequest request, HttpSession session) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        _LOG.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                _LOG.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                _LOG.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                _LOG.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                _LOG.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                _LOG.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                session.setAttribute(Const.ServerIP, ip);
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
    
    public String getUserGovStatus(String userAid, String mallId, String platformId) {
        if (!Util.isCon(userAid)) {
            return Const.USER_APPROVE_STS_0;
        }
        BruAuthentication currentResult = new BruAuthentication();

        DBPersistenceManager pm=null;
        try {

        	pm=DBAccessHelper.getPMByName(Const.DataSourceName);
	        DataSet ds=(DataSet)pm.executeQuery("select     USER_AID, USER_ID, password, user_type, last_login_date, USER_STS_CD, CREATED_USER_ID, " + 
	        		"    CREATED_DATE, UPDATED_USER_ID, UPDATED_DATE, DELETE_TYPE  from BRU_AUTHENTICATION  where DELETE_TYPE=1  AND  USER_AID = '"+userAid+"' ");     
	        if (ds!=null&&ds.next()) {
	        	
	        	currentResult.setUserAid(ds.getString("USER_AID"));
	        	currentResult.setUserId(ds.getString("USER_ID"));
	        	currentResult.setPassword(ds.getString("password"));
	        	currentResult.setUserType(ds.getString("user_type"));
	        	currentResult.setLastLoginDate(ds.getDate("last_login_date"));
	        	currentResult.setUserStsCd(ds.getString("USER_STS_CD"));
	        	currentResult.setDeleteType(ds.getString("DELETE_TYPE"));
            }
        }catch (Exception e) {
        	_LOG.error("LoginController3：异常 " + userAid + "认证信息获取失败 Author：xh");
        }finally{
        	if(pm!=null){
        		pm.close();
        	}
        }
        
        if (Util.isCon(currentResult)) {
            return currentResult.getUserStsCd();
        }
        return Const.USER_APPROVE_STS_0;
    }
    
    public String getUserMallStatus(String userAid, String mallId, String platformId) {
        if (!Util.isCon(userAid)) {
            return Const.USER_APPROVE_STS_0;
        }

        BrtCmnMallUserShip brtCmnMallUserShip = new BrtCmnMallUserShip();
        brtCmnMallUserShip.setPlatformId(platformId);
        brtCmnMallUserShip.setMallId(mallId);
        BrtCmnMallUserShip currentResult = new BrtCmnMallUserShip();
        
        DBPersistenceManager pm=null;
        try {

        	pm=DBAccessHelper.getPMByName(Const.DataSourceName);
	        DataSet ds=(DataSet)pm.executeQuery(" select " + 
	        		"    SHIP_ID, PLATFORM_ID, MALL_ID, USER_AID, APPROVE_STS_CD, APPROVE_USER_ID, APPROVE_DATE,  " + 
	        		"    CREATED_USER_ID, CREATED_DATE, UPDATED_USER_ID, UPDATED_DATE, DELETE_TYPE " + 
	        		"        from brt_cmn_mall_user_ship where DELETE_TYPE=1  AND  USER_AID = '"+userAid+"' "
	        				+ " AND PLATFORM_ID = '"+platformId+"' "
	        				+ " AND MALL_ID ='"+mallId+"'");     
	        if (ds!=null&&ds.next()) {
	        	
	        	currentResult.setUserAid(ds.getString("USER_AID"));
	        	currentResult.setShipId(ds.getString("SHIP_ID"));
	        	currentResult.setPlatformId(ds.getString("PLATFORM_ID"));
	        	currentResult.setMallId(ds.getString("MALL_ID"));
	        	currentResult.setApproveStsCd(ds.getString("APPROVE_STS_CD"));
	        	currentResult.setApproveUserId(ds.getString("APPROVE_USER_ID"));
	        	currentResult.setDeleteType(ds.getString("DELETE_TYPE"));
	        	currentResult.setApproveDate(ds.getDate("APPROVE_DATE"));
            }
        }catch (Exception e) {
        	_LOG.error("LoginController4：异常 " + userAid + "认证信息获取失败 Author：xh");
        }finally{
        	if(pm!=null){
        		pm.close();
        	}
        }
       
        if (Util.isCon(currentResult)) {
            return currentResult.getApproveStsCd();
        }
        return Const.USER_APPROVE_STS_0;
    }
    
    public Map<String, Object> countMsgSts(String userAid) {
        Map<String, Object> smap = new HashMap<>();
        BruMessageInfo bmi = new BruMessageInfo();
        if (Util.isCon(userAid)) {
            bmi.setMsgReciver(userAid);
            DBPersistenceManager pm=null;
            try {

            //系统未阅
	            pm=DBAccessHelper.getPMByName(Const.DataSourceName);
	            int sCount=0;
		        DataSet ds=(DataSet) pm.executeQuery("SELECT COUNT(MSG_ID) AS MSG_ID FROM bru_message_info"
	            		+ " where   IS_READ = 0 "
	            		+ "  AND MSG_RECIVER = '"+userAid+"'"
	            		+ " AND MSG_TYPE = '"+Const.MSG_TYPE_SYSTEM+"' ");
		        if (ds!=null&&ds.next()) {
		        	sCount=ds.getInt("MSG_ID");
		        }
	            smap.put("systemCount", sCount);
	            int cCount =0;
	            
	            DataSet dsCfm=(DataSet) pm.executeQuery("SELECT COUNT(MSG_ID) AS MSG_ID FROM bru_message_info"
	            		+ " where   IS_READ = 0 "
	            		+ "  AND MSG_RECIVER = '"+userAid+"'"
	            		+ " AND MSG_TYPE = '"+Const.MSG_TYPE_CONFIRM+"' ");
	            if (dsCfm!=null&&dsCfm.next()) {
	            	cCount=dsCfm.getInt("MSG_ID");
		        }
	            smap.put("confirmCount", cCount);
	            smap.put("allCount", sCount + cCount);
            }catch (Exception e) {
            	_LOG.error("LoginController5：异常 " + userAid + "认证信息获取失败 Author：xh");
            }finally{
            	if(pm!=null){
            		pm.close();
            	}
            }
        }

        return smap;
    }
    
    
    public List<BruRoleDefinition> queryRoleListByUserIdAndGroupType(String userId, String userType, String groupType) {

        // 初始化返回值
        List<BruRoleDefinition> rtn = new ArrayList<>();
        // 根据登陆的用户ID获取对用的所有用户角色
        List<BruRoleDefinition> lst = queryRoleListByUserId(userId);
        if (lst != null && lst.size() > 0) {
            // 如果登陆用户为个人用户时
            if (Const.MENU_BELONG_USER_TYPE_2.equals(userType)) {
                for (BruRoleDefinition bean : lst) {
                    if (bean.getRoleType().equals(groupType) || Const.MENU_BELONG_USER_TYPE_2.equals(bean.getRoleType())) {
                        rtn.add(bean);
                    }
                }
            } else {
                for (BruRoleDefinition bean : lst) {
                    if (bean.getRoleType().equals(userType)) {
                        rtn.add(bean);
                    }
                }
            }
        }
        return rtn;
    }
    
    public List<BruRoleDefinition> queryRoleListByUserId(String userId) {
        List<BruRoleDefinition> roleList = new ArrayList<>();
        List<String> roleIds = new ArrayList<>();
        
        DBPersistenceManager pm=null;
        try {

        //系统未阅
            pm=DBAccessHelper.getPMByName(Const.DataSourceName);
		        if (Util.isCon(userId)) {
		            BruAuthentication ba = new BruAuthentication();
		            ba.setUserId(userId);
		            DataSet ds=(DataSet)pm.executeQuery("select     USER_AID, USER_ID, password, user_type, last_login_date, USER_STS_CD, CREATED_USER_ID, " + 
			        		"    CREATED_DATE, UPDATED_USER_ID, UPDATED_DATE, DELETE_TYPE  from BRU_AUTHENTICATION  where DELETE_TYPE=1  AND  binary USER_ID = '"+userId+"' ");     
			        if (ds!=null&&ds.next()) {
			        	
			        	ba.setUserAid(ds.getString("USER_AID"));
			        	ba.setUserId(ds.getString("USER_ID"));
			        	ba.setPassword(ds.getString("password"));
			        	ba.setUserType(ds.getString("user_type"));
			        	ba.setLastLoginDate(ds.getDate("last_login_date"));
			        	ba.setUserStsCd(ds.getString("USER_STS_CD"));
			        	ba.setDeleteType(ds.getString("DELETE_TYPE"));
		            }
			        
		            if (Util.isCon(ba)) {
		                String userAid = ba.getUserAid();
		                String userType = ba.getUserType();
		                //取直接关联用户角色信息
		               
		                List<BruRoleUser> ruList = new ArrayList();
                		DataSet dsRole=(DataSet)pm.executeQuery("select    USER_AID, ROLE_ID, CREATED_USER_ID, UPDATED_USER_ID,"
                				+ "  CREATED_DATE, UPDATED_DATE, " + 
                				"    DELETE_TYPE   from bru_role_user"
       			        		+ "  where DELETE_TYPE=1   AND  USER_AID = '"+userAid+"' ");     
       			        while (dsRole!=null&&dsRole.next()) {
       			            BruRoleUser bru = new BruRoleUser();
       			            bru.setUserAId(dsRole.getString("USER_AID"));
       			        	bru.setRoleId(dsRole.getString("ROLE_ID"));
       			        	bru.setDeleteType(dsRole.getString("DELETE_TYPE"));
       			        	ruList.add(bru);
       		            }
		                if (Util.isCon(ruList) && ruList.size() > 0) {
		                    for (BruRoleUser bruRoleUser : ruList) {
		                        roleIds.add(bruRoleUser.getRoleId());
		                    }
		                }
		            }
		        }
		        //去重
		        List<String> listWithoutDup = new ArrayList<>(new HashSet<>(roleIds));
		        if (Util.isCon(listWithoutDup) && listWithoutDup.size() > 0) {
		            for (String s : listWithoutDup) {
		                BruRoleDefinition br = new BruRoleDefinition();
		                
		                DataSet dsRoleD=(DataSet)pm.executeQuery(" select   ROLE_ID, ROLE_TYPE, ROLE_NAME, PLATFORM_ID,"
		                		+ "  ROLE_DESCRIPTION, CREATED_USER_ID, UPDATED_USER_ID, " + 
		                		"    CREATED_DATE, UPDATED_DATE, DELETE_TYPE, SELECT_FLAG" + 
                				"        from bru_role_definition "
       			        		+ "  where DELETE_TYPE=1   AND  ROLE_ID = '"+s+"' ");     
       			        if (dsRoleD!=null&&dsRoleD.next()) {
       			        	br.setRoleId(dsRoleD.getString("ROLE_ID"));
       			        	br.setRoleType(dsRoleD.getString("ROLE_TYPE"));
       			        	br.setRoleName(dsRoleD.getString("ROLE_NAME"));
       			        	br.setPlatformId(dsRoleD.getString("PLATFORM_ID"));
       			        	br.setRoleDescription(dsRoleD.getString("ROLE_DESCRIPTION")+"");
       			        	br.setSelectFlag(dsRoleD.getString("SELECT_FLAG"));
       			        }
		                
		                roleList.add(br);
		            }
		        }
        }catch (Exception e) {
        	_LOG.error("LoginController6：异常 " + userId + "认证信息获取失败 Author：xh");
        }finally{
        	if(pm!=null){
        		pm.close();
        	}
        }    
        return roleList;
    }
    
	/**
	 * 根据平台ID 商城ID 系统ID 用户对应的角色信息获取对应的头菜单
	 * 
	 * @param platformId
	 * @param mallId
	 * @param systemId
	 * @param userRoles
	 * @param approveFlag
	 * @return
	 */
	public List<MenuInfo> getLoginUserTopMenuInfo(String platformId, String mallId, String systemId,
			List<String> userRoles, char approveFlag) {

		// 初始化返回值
		List<MenuInfo> rtn = new ArrayList<>();
		List<BrtCmnMenuMall> lst = new ArrayList<BrtCmnMenuMall>();
		String menuTypes = Joiner.on(",").join(userRoles);
		DBPersistenceManager pm = null;
		try {
			pm = DBAccessHelper.getPMByName(Const.DataSourceName);
			String sql="SELECT bcmm.* FROM brt_cmn_menu_mall bcmm  JOIN brt_cmn_menu_role_ship bcmrs ON (bcmrs.PLATFORM_ID = bcmm.PLATFORM_ID AND bcmrs.MALL_ID = bcmm.MALL_ID "
					+ "AND bcmrs.SYSTEM_ID = bcmm.SYSTEM_ID AND bcmrs.MALL_MENU_ID = bcmm.MALL_MENU_ID AND bcmm.APPROVE_FLAG = '0') WHERE bcmm.PLATFORM_ID = '"
					+ platformId + "' AND bcmm.MALL_ID = '" + mallId + "' AND bcmm.SYSTEM_ID = '" + systemId;
			sql=sql+ "' AND bcmrs.ROLE_ID IN ('" + menuTypes + "' )  "
					+ " GROUP BY  bcmm.PLATFORM_ID,bcmm.MALL_ID, bcmm.SYSTEM_ID,bcmm.MENU_LEVEL,bcmm.PARENT_MENU_ID,bcmm.MENU_TYPE,bcmm.MENU_SEQ ORDER BY bcmm.MENU_TYPE,bcmm.MENU_SEQ";
		
			
					
			System.out.println(sql);
			DataSet ds = (DataSet) pm.executeQuery(sql);
			while (ds != null && ds.next()) {
				MenuInfo menuInfo = new MenuInfo();

				BrtCmnMenuMall brt = new BrtCmnMenuMall();

				brt.setTargetUrl(ds.getString("TARGET_URL"));
				brt.setMallId(ds.getString("MALL_ID"));
				brt.setPlatformId(ds.getString("PLATFORM_ID"));
				brt.setPlatformMenuId(ds.getString("PLATFORM_MENU_ID"));
				brt.setSystemId(ds.getString("SYSTEM_ID"));
				brt.setMenuLevel(ds.getString("MENU_LEVEL"));
				brt.setParentMenuId(ds.getString("PARENT_MENU_ID"));
				brt.setMenuType(ds.getString("MENU_TYPE"));
				brt.setMenuName(ds.getString("MENU_NAME"));
				brt.setMenuImgId(ds.getString("MENU_IMG_ID"));
				brt.setTargetType(ds.getString("TARGET_TYPE"));
				brt.setUrlType(ds.getString("URL_TYPE"));
				brt.setDeleteType(ds.getString("DELETE_TYPE"));
				brt.setMallMenuId(ds.getString("MALL_MENU_ID"));

				lst.add(brt);

			}
			// 去掉重复后的菜单信息集合
			List<BrtCmnMenuMall> lst1 = this.romoveSameMenuInfo(lst);
			// 整形菜单信息
			this.setMenuList(lst1, rtn);
			if (rtn != null && rtn.size() > 0) {
				return rtn;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pm != null) {
				pm.close();
			}
		}

		return null;
	}
	
	/**
	 * 从整形后的菜单数据信息中 去掉重复的菜单
	 * 
	 * @param inLstMenuInfo
	 * @return
	 */
	private List<BrtCmnMenuMall> romoveSameMenuInfo(List<BrtCmnMenuMall> inLstMenuInfo) {

		// 初始化返回值
		List<BrtCmnMenuMall> rtnLst = null;
		if (inLstMenuInfo != null && inLstMenuInfo.size() > 0) {
			rtnLst = new ArrayList<>();
			for (BrtCmnMenuMall bean : inLstMenuInfo) {
				// 获取当前菜单ID
				String menuId = bean.getMallMenuId();
				boolean addFlag = true;
				for (BrtCmnMenuMall bean1 : rtnLst) {
					// 获取当前菜单ID
					String menuId1 = bean1.getMallMenuId();
					if (menuId.equals(menuId1)) {
						addFlag = false;
						break;
					}
				}
				if (addFlag) {
					rtnLst.add(bean);
				}
			}
		}
		return rtnLst;
	}

	/**
	 * 根据取得的菜单信息 整理出要显示的菜单形
	 * 
	 * @param inMenuInfoList
	 * @param rtn
	 */
	private void setMenuList(List<BrtCmnMenuMall> inMenuInfoList, List<MenuInfo> rtn) {

		if (inMenuInfoList != null && inMenuInfoList.size() > 0) {
			MenuInfo menuInfo;
			List<BrtCmnMenuMall> lst;
			for (BrtCmnMenuMall bean : inMenuInfoList) {
				// 菜单头判断
				if ((bean.getParentMenuId() == null || bean.getParentMenuId().isEmpty())
						&& bean.getMenuLevel().equals(Const.MENU_LEVEL_1)) {
					menuInfo = new MenuInfo();
					// 菜单ID取得
					String menuId = bean.getMallMenuId();
					String menuType = bean.getMenuType();
					menuInfo.setFstMenu(bean);
					lst = new ArrayList<>();
					for (BrtCmnMenuMall secBean : inMenuInfoList) {
						// 查询出二级菜单所属的一级菜单
						if (null != secBean.getParentMenuId() && !secBean.getParentMenuId().isEmpty()
								&& menuId.equals(secBean.getParentMenuId())
								&& secBean.getMenuLevel().equals(Const.MENU_LEVEL_2)
								&& secBean.getMenuType().equals(menuType)) {
							lst.add(secBean);
						}
					}
					if (lst != null && lst.size() > 0) {
						menuInfo.setSecMenu(lst);
					}
					rtn.add(menuInfo);
				}
			}
		}
	}

	public List<MenuInfo> getLoginUserLeftMenuInfo(String platformId, String mallId, String systemId,
			List<String> userRoles, char approveFlag) {

		// 初始化返回值
		List<MenuInfo> rtn;
		if (platformId != null && !platformId.isEmpty() && mallId != null && !mallId.isEmpty() && systemId != null
				&& !systemId.isEmpty() && userRoles != null && userRoles.size() > 0) {
			rtn = new ArrayList<>();
			
			List<BrtCmnMenuMall> lst = new ArrayList<BrtCmnMenuMall>();
			String menuTypes = Joiner.on(",").join(userRoles);
			DBPersistenceManager pm = null;
			try {
				pm = DBAccessHelper.getPMByName(Const.DataSourceName);
				String sql="SELECT bcmm.* FROM brt_cmn_menu_mall bcmm  JOIN brt_cmn_menu_role_ship bcmrs ON (bcmrs.PLATFORM_ID = bcmm.PLATFORM_ID AND bcmrs.MALL_ID = bcmm.MALL_ID "
						+ "AND bcmrs.SYSTEM_ID = bcmm.SYSTEM_ID AND bcmrs.MALL_MENU_ID = bcmm.MALL_MENU_ID AND bcmm.APPROVE_FLAG = '0') WHERE bcmm.PLATFORM_ID = '"
						+ platformId + "' AND bcmm.MALL_ID = '" + mallId + "' AND bcmm.SYSTEM_ID = '" + systemId;
			
				sql=sql+ "' AND bcmrs.ROLE_ID IN ('" + menuTypes + "' )   "
							   + " GROUP BY  bcmm.PLATFORM_ID,bcmm.MALL_ID, bcmm.SYSTEM_ID,bcmm.MENU_LEVEL,bcmm.PARENT_MENU_ID,bcmm.MENU_TYPE,bcmm.MENU_SEQ ORDER BY bcmm.MENU_TYPE,bcmm.MENU_SEQ";
				
				System.out.println(sql);
				DataSet ds = (DataSet) pm.executeQuery(sql);
				while (ds != null && ds.next()) {
					MenuInfo menuInfo = new MenuInfo();

					BrtCmnMenuMall brt = new BrtCmnMenuMall();

					brt.setTargetUrl(ds.getString("TARGET_URL"));
					brt.setMallId(ds.getString("MALL_ID"));
					brt.setPlatformId(ds.getString("PLATFORM_ID"));
					brt.setPlatformMenuId(ds.getString("PLATFORM_MENU_ID"));
					brt.setSystemId(ds.getString("SYSTEM_ID"));
					brt.setMenuLevel(ds.getString("MENU_LEVEL"));
					brt.setParentMenuId(ds.getString("PARENT_MENU_ID"));
					brt.setMenuType(ds.getString("MENU_TYPE"));
					brt.setMenuName(ds.getString("MENU_NAME"));
					brt.setMenuImgId(ds.getString("MENU_IMG_ID"));
					brt.setTargetType(ds.getString("TARGET_TYPE"));
					brt.setUrlType(ds.getString("URL_TYPE"));
					brt.setDeleteType(ds.getString("DELETE_TYPE"));
					brt.setMallMenuId(ds.getString("MALL_MENU_ID"));

					lst.add(brt);

				}
				// 去掉重复后的菜单信息集合
				List<BrtCmnMenuMall> lst1 = this.romoveSameMenuInfo(lst);
				// 整形菜单信息
				this.setMenuList(lst1, rtn);
				if (rtn != null && rtn.size() > 0) {
					return rtn;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (pm != null) {
					pm.close();
				}
			}

		}
		return null;
	}
}
