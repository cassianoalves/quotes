package com.cassianoalves.quotes.configuration;

import com.cassianoalves.quotes.component.EmailComponent;
import com.cassianoalves.quotes.component.EmailComponentImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Configuration
public class ApplicationConfig {

    @Bean
    public MailSender mailSender() {

        JavaMailSenderImpl bean = new JavaMailSenderImpl();

        bean.setHost(ObjectUtils.defaultIfNull(System.getProperty("quotes.mail.host"), "smtp.gmail.com"));
        bean.setPort(Integer.parseInt(ObjectUtils.defaultIfNull(System.getProperty("quotes.mail.port"), "587")));
        bean.setUsername(System.getProperty("quotes.mail.username"));
        bean.setPassword(System.getProperty("quotes.mail.password"));
        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.auth", true);
        mailProps.put("mail.smtp.starttls.enable", true);
        bean.setJavaMailProperties(mailProps);

        return bean;
    }

    @Bean
    public EmailComponent emailComponent() {
        EmailComponentImpl emailComponent = new EmailComponentImpl();
        emailComponent.setWebAppRoot(ObjectUtils.defaultIfNull(System.getProperty("quotes.web.root.url"), "http://localhost:9000/#"));
        emailComponent.setMailSender(mailSender());
        return emailComponent;
    }

    @Bean
    public Random random() {
        return new Random(new Date().getTime());
    }

}
