package com.kivi.framework.web.kaptcha.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.kivi.framework.properties.KtfProperties;
import com.kivi.framework.util.FileUtil;

/**
 * 验证码生成
 */
@Controller
@RequestMapping( "/kaptcha" )
@ConditionalOnBean( value = { Producer.class } )
public class KaptchaController {
    private static final Logger log = LoggerFactory.getLogger(KaptchaController.class);
    @Autowired
    Producer                    producer;

    @Autowired
    private KtfProperties       ktfProperties;

    /**
     * 生成验证码
     */
    @RequestMapping( "" )
    public void index( HttpServletRequest request, HttpServletResponse response ) {
        HttpSession session = request.getSession();

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = producer.createText();

        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            // write the data out
            ImageIO.write(bi, "jpg", out);

            out.flush();
        }
        catch (IOException e) {
            log.error("验证码生成异常", e);
        }

        finally {
            try {
                out.close();
            }
            catch (IOException e) {
                log.error("验证码输出异常", e);
            }
        }
    }

    @RequestMapping( "/{pictureId}" )
    public void renderPicture( @PathVariable( "pictureId" ) String pictureId, HttpServletResponse response ) {
        String path = ktfProperties.getCommon().getFileUploadPath() + pictureId + ".jpg";
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        }
        catch (Exception e) {
            // 如果找不到图片就返回一个默认图片
            try {
                response.sendRedirect("/static/img/admin.png");
            }
            catch (IOException e1) {
                log.error("返还默认图片异常", e1);
            }
        }
    }

}
