package com.example.MultiTenent.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.MultiTenent.Repositorys.db2Repository"},
        mongoTemplateRef = db2Config.MONGO_TEMPLATE)
public class db2Config {
    protected static final String MONGO_TEMPLATE="db2MongoTemplate";
}
