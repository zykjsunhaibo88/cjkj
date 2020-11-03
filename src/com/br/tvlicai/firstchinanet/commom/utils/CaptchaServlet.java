package com.br.tvlicai.firstchinanet.commom.utils;

import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by yuyang on 2014/11/12.
 */
public class CaptchaServlet extends HttpServlet {
    static final Logger _LOG = LoggerFactory.getLogger(CaptchaServlet.class);
    public static final String KEY_CAPTCHA = "SE_KEY_MM_CODE";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置相应类型,告诉浏览器输出的内容为图片
        resp.setContentType("image/jpeg");
        // 不缓存此内容
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expire", 0);
        try {

            HttpSession session = req.getSession();

            CaptchaUtil tool = new CaptchaUtil();
            StringBuffer code = new StringBuffer();
            BufferedImage image = tool.genRandomCodeImage(code);
            session.removeAttribute(KEY_CAPTCHA);
            session.setAttribute(KEY_CAPTCHA, code.toString());

            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", resp.getOutputStream());

        } catch (Exception e) {
            _LOG.error("异常信息", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
