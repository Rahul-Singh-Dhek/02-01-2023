package com.example.MultiTenent.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="rahulUser")
public class Model1 {

    @Id
    private String id;

    @Indexed(unique = true)
    private String userName;
    private String fullName;
    private String address;
    private String mobileNumber;
    private String currentOrganization;
    private String dataBase;


}
