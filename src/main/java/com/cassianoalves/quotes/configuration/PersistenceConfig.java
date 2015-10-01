package com.cassianoalves.quotes.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackages = "com.cassianoalves.quotes.repository")
public class PersistenceConfig {
//    @Bean
//    public MongoDbFactory mongo() throws UnknownHostException {
//        return new SimpleMongoDbFactory(new MongoClientURI("mongodb://localhost/quotes"));
//    }

//    public @Bean Mongo mongo() throws Exception {
//        return new Mongo("localhost");
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception{
//        return new MongoTemplate(mongo(), "quotes");
//    }
}
