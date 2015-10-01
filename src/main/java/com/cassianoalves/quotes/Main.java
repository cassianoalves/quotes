package com.cassianoalves.quotes;

import com.cassianoalves.quotes.configuration.ApplicationConfig;
import com.cassianoalves.quotes.configuration.PersistenceConfig;
import com.cassianoalves.quotes.controller.InviteService;
import com.cassianoalves.quotes.model.Invite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class, args);

    }
}
