package com.cassianoalves.quotes.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackages = "com.cassianoalves.quotes.repository")
public class PersistenceConfig {}
