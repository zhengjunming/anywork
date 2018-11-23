package com.qg.anywork.web.controller;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.common.ParamEmptyException;
import com.qg.anywork.service.MailService;
import com.qg.anywork.service.UserService;
import com.qg.anywork.util.Encryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 工具类访问接口
 *
 * @author FunriLy
 * @date 2017/8/18
 * From small beginnings comes great things.
 */
@Controller
@RequestMapping("/utils")
@CrossOrigin
@Slf4j
public class UtilController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    // TODO 各种重定向页面

    /**
     * 验证邮箱秘钥，注册用户
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String checkRegister(HttpServletRequest request) {
        String email = request.getParameter("email");
        String ciphertext = request.getParameter("ciphertext");
        if (email == null || "".equals(email) || ciphertext == null || "".equals(ciphertext)) {
            return "redirect:../html/failure.html";
        }
        if (ciphertext.equals(Encryption.getMD5(email))) {
            // 验证正确
            try {
                userService.register(email);
            } catch (Exception e) {
                return "redirect:../html/failure.html";
            }
            return "redirect:../html/success.html";
        } else {
            return "redirect:../html/failure.html";
        }
    }

    /**
     * 验证邮箱秘钥,修改密码
     */
    @GetMapping("/reset")
    public String resetPassword(HttpServletRequest request) {
        String email = request.getParameter("email");
        String ciphertext = request.getParameter("ciphertext");
        if (email == null || "".equals(email) || ciphertext == null || "".equals(ciphertext)) {
            throw new ParamEmptyException(StatEnum.PARAM_IS_EMPTY);
        }
        if (ciphertext.equals(Encryption.getMD5(email))) {
            request.getSession().setAttribute("email", email);
            return "redirect:../html/newPassword.html";
        }
        return null;
    }

    /**
     * 生成图片验证码
     *
     * @param request  request
     * @param response response
     * @return 验证码
     */
    @RequestMapping(value = "/valcode", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String verification(HttpServletRequest request, HttpServletResponse response) {

        // 告知浏览当作图片处理
        response.setContentType("image/jpeg");
        // 告诉浏览器不缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        // 产生由4位数字构成的验证码
        int length = 4;
        StringBuilder valcode = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            valcode.append(rd.nextInt(10));
        }
        // 把产生的验证码存入到Session中
        HttpSession session = request.getSession();
        session.setAttribute("valcode", valcode.toString());

        // 产生图片
        int width = 120;
        int height = 30;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取一个Graphics
        Graphics g = img.getGraphics();
        // 填充背景色
        g.setColor(Color.WHITE);
        //填充指定的矩形
        g.fillRect(0, 0, width, height);
        // 填充干扰线50
        for (int i = 0; i < 50; i++) {
            g.setColor(new Color(rd.nextInt(100) + 155, rd.nextInt(100) + 155, rd.nextInt(100) + 155));
            g.drawLine(rd.nextInt(width), rd.nextInt(height), rd.nextInt(width), rd.nextInt(height));
        }
        // 绘制边框
        g.setColor(Color.GRAY);
        //边框
        g.drawRect(0, 0, width - 1, height - 1);
        // 绘制验证码
        for (int i = 0; i < length; i++) {
            g.setColor(new Color(rd.nextInt(150), rd.nextInt(150), rd.nextInt(150)));
            g.drawString(valcode.charAt(i) + "", width / valcode.length() * i + 2, 18);
        }
        // 输出图像
        g.dispose();
        try {
            ImageIO.write(img, "jpeg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return "html/failure.html";
        }
        return "html/success.html";
    }
}
