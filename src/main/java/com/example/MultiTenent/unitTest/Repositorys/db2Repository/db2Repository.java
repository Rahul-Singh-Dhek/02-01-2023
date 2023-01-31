package com.example.MultiTenent.unitTest.Repositorys.db2Repository;

import com.example.MultiTenent.unitTest.Models.Model1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface db2Repository extends MongoRepository<Model1,String> {
    @Query
    List<Model1> findByUserName(String userName);
}
