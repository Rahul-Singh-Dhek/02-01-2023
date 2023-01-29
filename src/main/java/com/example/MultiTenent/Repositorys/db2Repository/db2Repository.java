package com.example.MultiTenent.Repositorys.db2Repository;

import com.example.MultiTenent.Models.Model1;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface db2Repository extends MongoRepository<Model1,String> {
    @Query
    List<Model1> findByUserName(String userName);
}
