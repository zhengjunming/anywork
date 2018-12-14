package com.qg.anywork.util;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.mail.MailSendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


/**
 * @author ming
 */
@Component
public class MailUtil {

    /**
     * 服务器地址
     */
    private final static String HOST = "qgstudio.org";

    /**
     * 登录URL
     */
    private final static String LOGIN_URL = "https://" + HOST + ":80/anywork/html/login.html";

    /**
     * 校验邮箱URL
     */
    private final static String CHECK_EMAIL_URL = "https://" + HOST + ":80/anywork/utils/check";

    /**
     * 忘记密码URL
     */
    private final static String PASSWORD_URL = "https://" + HOST + ":80/anywork/utils/reset";

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 向用户邮箱发送邮件
     *
     * @param email    用户邮箱地址
     * @param userName 用户昵称
     * @param index    标志
     *                 <p>
     *                 index == 1 发送用户注册邮件
     *                 index == 2 发送忘记密码邮件
     */
    public void send(String email, String userName, int index) {
        MimeMessage message;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String nick = MimeUtility.encodeText("AnyWork");
            helper.setFrom(new InternetAddress(nick + " <" + from + ">"));
            helper.setTo(email);
            if (index == 1) {
                helper.setSubject("主题：用户注册验证");
                helper.setText(registerHtmlText(email, userName), true);
            } else if (index == 2) {
                helper.setSubject("主题：用户忘记密码");
                helper.setText(passwordHtmlText(email, userName), true);
            } else {
                throw new Exception("未知参数");
            }
        } catch (Exception e) {
            throw new MailSendException(StatEnum.MAIL_SEND_FAIL);
        }
        javaMailSender.send(message);
    }

    private static String registerHtmlText(String email, String username) {
        final String ciphertext = Encryption.getMD5(email);
        return "<style type=\"text/css\">html,\n" +
                "    body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "    }\n" +
                "</style>\n" +
                "<center>\n" +
                "<table style=\"background:#f6f7f2;font-size:13px;font-family:microsoft yahei;\">\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td>\n" +
                "\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600px\">\n" +
                "\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-top:120px;background: url(http://7xi9bi.com1.z0.glb.clouddn.com/35069/2015/07/20/5891eacba5ba41f389168121f08be02f.jpg) no-repeat;\" width=\"538px\">\n" +
                "\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td height=\"70px\" valign=\"middle\" width=\"100%\"><img alt=\"logo\" src=\"https://www.tuchuang001.com/images/2017/10/15/1686ccdd7919429a8beeb4f3f15d5eb1.png\" style=\"margin-left:50px;\" /></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#fff;height:411px;\" width=\"465px\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"color:#666;line-height:1.5\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"width:360px;text-align:left;margin-top:50px;margin-bottom:80px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<p>亲爱的" + "用户 " + username + "，您好：</p>\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"text-indent:2em\">\n" +
                "请点击下面的链接完成用户注册(5分钟内有效)：<br />" +
                "<h1>用户注册</h1><br/>" +
                "<a href='" + CHECK_EMAIL_URL + "?email=" + email + "&ciphertext=" + ciphertext + "'>点击完成注册</a></p>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"margin-bottom:40px;\"><a href=\"" + LOGIN_URL + "\" style=\"display:inline-block;width:139px;height:38px;line-height:38px;color:#fff;font-size:14px;vertical-align:middle;background:url(http://7xi9bi.com1.z0.glb.clouddn.com/35069/2015/07/20/0edb116f982044ba85ecd313f20e881c.jpg);text-decoration:none\">AnyWork</a></div>\n" +
                "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:1px dashed #ccc;margin:20px\">&nbsp;</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<td height=\"37px\" style=\"background:#dededc\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t</tbody>\n" +
                "</table>\n" +
                "</center>";
    }


    private static String passwordHtmlText(String email, String username) {
        final String ciphertext = Encryption.getMD5(email);
        return
                "<style type=\"text/css\">html,\n" +
                        "    body {\n" +
                        "        margin: 0;\n" +
                        "        padding: 0;\n" +
                        "    }\n" +
                        "</style>\n" +
                        "<center>\n" +
                        "<table style=\"background:#f6f7f2;font-size:13px;font-family:microsoft yahei;\">\n" +
                        "\t<tbody>\n" +
                        "\t\t<tr>\n" +
                        "\t\t\t<td>\n" +
                        "\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600px\">\n" +
                        "\t\t\t\t<tbody>\n" +
                        "\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                        "\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-top:120px;background: url(http://7xi9bi.com1.z0.glb.clouddn.com/35069/2015/07/20/5891eacba5ba41f389168121f08be02f.jpg) no-repeat;\" width=\"538px\">\n" +
                        "\t\t\t\t\t\t\t<tbody>\n" +
                        "\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t<td height=\"70px\" valign=\"middle\" width=\"100%\"><img alt=\"logo\" src=\"https://www.tuchuang001.com/images/2017/10/15/1686ccdd7919429a8beeb4f3f15d5eb1.png\" style=\"margin-left:50px;\" /></td>\n" +
                        "\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                        "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#fff;height:411px;\" width=\"465px\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"color:#666;line-height:1.5\" valign=\"top\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"width:360px;text-align:left;margin-top:50px;margin-bottom:80px;\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<p>亲爱的" + "用户 " + username + "，您好：</p>\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<p style=\"text-indent:2em\">\n" +
                        "请点击下面的链接修改用户密码：<br />" +
                        "<h1>忘了密码</h1><br/>" +
                        "<a href='" + PASSWORD_URL + "?email=" + email + "&ciphertext=" + ciphertext + "'>点击修改密码</a></p>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"margin-bottom:40px;\"><a href=\"" + LOGIN_URL + "\" style=\"display:inline-block;width:139px;height:38px;line-height:38px;color:#fff;font-size:14px;vertical-align:middle;background:url(http://7xi9bi.com1.z0.glb.clouddn.com/35069/2015/07/20/0edb116f982044ba85ecd313f20e881c.jpg);text-decoration:none\">AnyWork</a></div>\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"border-top:1px dashed #ccc;margin:20px\">&nbsp;</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<td height=\"37px\" style=\"background:#dededc\">&nbsp;</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                        "\t\t\t\t\t\t\t\t\t</table>\n" +
                        "\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t</tbody>\n" +
                        "\t\t\t\t\t\t</table>\n" +
                        "\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t</tbody>\n" +
                        "\t\t\t</table>\n" +
                        "\t\t\t</td>\n" +
                        "\t\t</tr>\n" +
                        "\t</tbody>\n" +
                        "</table>\n" +
                        "</center>"
                ;
    }

}
