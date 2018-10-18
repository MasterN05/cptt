package com.znxsx.cptt.service;


import com.znxsx.cptt.config.MyConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * MailServiceImpl
 *
 * @author xiongshixin
 * @date 2018/10/17
 **/
@Service
public class MailServiceImpl implements MailService {

    //邮件的发送者
    @Value("${spring.mail.username}")
    private String from;

    //注入MailSender
    @Autowired
    private JavaMailSender mailSender;

    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;
    @Autowired
    private MyConfig myConfig;

    /**
     * @param params       发送邮件的主题对象 object
     * @param title        邮件标题
     * @param templateName 模板名称
     */
    @Override
    public void sendMessageMail(Object params, String title, String templateName) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(InternetAddress.parse(myConfig.getEmail()));//发送给谁
            helper.setSubject("【" + title + "-" + LocalDate.now() + ":" + LocalTime.now().withNano(0) + "】");//邮件标题

            Map<String, Object> model = new HashMap<>();
            model.put("params", params);
            try {
                Template template = configurer.getConfiguration().getTemplate(templateName);
                try {
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

                    helper.setText(text, true);
                    mailSender.send(mimeMessage);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}