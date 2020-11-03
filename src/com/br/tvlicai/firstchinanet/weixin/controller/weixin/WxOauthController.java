package com.br.tvlicai.firstchinanet.weixin.controller.weixin;

import com.br.tvlicai.firstchinanet.weixin.controller.weixin.ws.WeixinWSImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangnan on 2016/9/25 0025.
 */
@Controller
@RequestMapping("/wxOauth")
public class WxOauthController {
    @Autowired
    private WeixinWSImpl service;
    /**
     * 取得用户授权跳转 weixin oauth2.0 接入授权后重定向
     *
     * <pre>
     * 指導手冊：http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html
     * 接入链接：http://服務器域名/wx/oauth2/?id={数据库后缀番号}&scope={snsapi_base/snsapi_userinfo}&state={自定码}&url={重定向的url}
     * eg:http://ss.cymain.com/wx/oauth2/?id=1&scope=snsapi_userinfo&state=1&url=http://ss.cymain.com/info/addInfo/
     * 自定码state：重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * </pre>
     *
     * @author GodSon
     */
    @RequestMapping("/oauth2")
    public void oauth2(HttpServletRequest req, HttpServletResponse res) {
        // 进入POST聊天处理
        try {
            service.oauth2(req, res);
        } catch (Exception e) {
        }
    }
}
