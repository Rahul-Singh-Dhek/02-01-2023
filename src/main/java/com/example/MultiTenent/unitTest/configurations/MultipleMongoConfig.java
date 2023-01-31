package com.example.MultiTenent.unitTest.configurations;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class MultipleMongoConfig {
    @Primary
    @Bean(name="db1Properties")
    @ConfigurationProperties(prefix="spring.data.mongodb.db1")
    public MongoProperties getdb1Props() throws Exception{
        return new MongoProperties();
    }

    @Bean(name = "db2Properties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.db2")
    public MongoProperties getdb2Props() throws Exception{
        return new MongoProperties();
    }

    @Primary
    @Bean(name="db1MongoTemplate")
    public MongoTemplate db1MongoTemplate() throws Exception{
        MongoTemplate mongoTemplate = new MongoTemplate(db1MongoDatabaseFactory(getdb1Props()));
        //Next line is for createing userName field unique
        mongoTemplate.indexOps("rahulUser").ensureIndex(new Index("userName", Sort.Direction.ASC).unique());
        return mongoTemplate;
    }

    @Bean(name="db2MongoTemplate")
    public MongoTemplate db2MongoTemplate() throws Exception{
        return new MongoTemplate(db2MongoDatabaseFactory(getdb2Props()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory db1MongoDatabaseFactory(MongoProperties mongo) throws Exception{
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }

    @Bean
    public MongoDatabaseFactory db2MongoDatabaseFactory(MongoProperties mongo) throws Exception{
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }



}
