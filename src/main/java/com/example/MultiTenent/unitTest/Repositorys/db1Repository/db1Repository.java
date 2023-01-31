package com.example.MultiTenent.unitTest.Repositorys.db1Repository;

import com.example.MultiTenent.unitTest.Models.Model1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface db1Repository extends MongoRepository<Model1,String> {

    @Query
    List<Model1> findByUserName(String userName);

    @Query
    void deleteByUserName(String empId);
}