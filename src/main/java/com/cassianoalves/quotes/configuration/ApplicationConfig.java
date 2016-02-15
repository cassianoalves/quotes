package com.cassianoalves.quotes.configuration;

import com.cassianoalves.quotes.component.EmailComponent;
import com.cassianoalves.quotes.component.EmailComponentImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Configuration
public class ApplicationConfig {

    @Bean
    public Random random() {
        return new Random(new Date().getTime());
    }

}
