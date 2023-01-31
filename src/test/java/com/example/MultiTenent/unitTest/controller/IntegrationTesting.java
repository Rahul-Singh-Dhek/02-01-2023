package com.example.MultiTenent.unitTest.service;

import com.example.MultiTenent.unitTest.Models.Model1;
import com.example.MultiTenent.unitTest.Repositorys.db1Repository.db1Repository;
import com.example.MultiTenent.unitTest.Repositorys.db2Repository.db2Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTesting {
    @LocalServerPort
    private int port;

    @Autowired
    private WebClient.Builder webTestClient;

    @MockBean
    private db1Repository db1;

    @MockBean
    private db2Repository db2;

    Model1 user;
    @BeforeEach
    public void startingMock(){
        user=new Model1("RSD12320","Vishal1","Vishal Singh Dhek", "Kondapur",
                "6393578389","Albanero","backupDataBase");
    }

    @AfterEach
    public void destroying(){
        user=null;
    }

    @Test
    public void Test_createUser(){
//        when(db1.save(user)).thenReturn(user);
        given(db1.save(user)).willReturn(user);
//        Model1 savedUser = service.createUser(1,user);
        //create User Api
        ResponseEntity<Model1> savedResponse = webTestClient.baseUrl("http://localhost:" + port)
                .build()
                .post()
                .uri("/user/create/1")
                .bodyValue(user)
                .retrieve().toEntity(Model1.class).block();
//        System.out.println(savedResponse.getBody());
        assertEquals(user.getUserName(),savedResponse.getBody().getUserName());

        //get user Api
        given(db1.findAll()).willReturn(List.of(user));
        ResponseEntity<List<Model1>> savedResponse1 = webTestClient.baseUrl("http://localhost:" + port)
                .build()
                .get()
                .uri("/user/allUsers/1")
                        .retrieve().toEntityList(Model1.class).block();
        System.out.println(savedResponse1.getBody());
        assertEquals(savedResponse1.getBody(),List.of(user));

//        Update Api
        given(db1.findByUserName("Vishal")).willReturn(List.of(user));
        ResponseEntity<Model1> savedResponse2=webTestClient.baseUrl("http://localhost:"+port)
                .build()
                .put()
                .uri("/user/update/Vishal")
                .bodyValue(user)
                .retrieve().toEntity(Model1.class).block();
//        System.out.println(savedResponse2.getBody());
        assertEquals(savedResponse2.getBody().getFullName().length(),user.getFullName().length());


    }
}
