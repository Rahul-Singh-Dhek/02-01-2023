package com.example.MultiTenent.unitTest.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.MultiTenent.Repositorys.db1Repository"},
    mongoTemplateRef = db1Config.MONGO_TEMPLATE)
public class db1Config {
    protected static final String MONGO_TEMPLATE="db1MongoTemplate";



}


