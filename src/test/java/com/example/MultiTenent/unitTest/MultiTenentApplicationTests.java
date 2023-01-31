package com.example.MultiTenent.unitTest;

import com.example.MultiTenent.unitTest.Models.Model1;
import com.example.MultiTenent.unitTest.Repositorys.db1Repository.db1Repository;
import com.example.MultiTenent.unitTest.Repositorys.db2Repository.db2Repository;
import com.example.MultiTenent.unitTest.Services.userService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class MultiTenentApplicationTests {

	@Autowired
	private userService service;

	@MockBean
	private db1Repository db1;

	@MockBean
	private db2Repository db2;
	@Test
	public void getUsersTestfromDB1(){
		when(db1.findAll()).thenReturn(Stream.of(
				new Model1("RSD12321","Rahul","Rahul Singh Dhek",
				"Kondapur","6393578389","Albanero","mainDataBase"),
				new Model1("RSD12320","Vishal","Vishal Singh Dhek", "Kondapur",
						"6393578389","Albanero","mainDataBase")).collect(Collectors.toList()));
		assertEquals(2,service.getUsers(1).size());
//		assertEquals(1,1);
	}

	@Test
	public void getUsersTestfromDB2(){
		when(db2.findAll()).thenReturn(Stream.of(
				new Model1("RSD12321","Rahul","Rahul Singh Dhek",
						"Kondapur","6393578389","Albanero","backupDataBase"),
				new Model1("RSD12320","Vishal","Vishal Singh Dhek", "Kondapur",
						"6393578389","Albanero","backupDataBase")).collect(Collectors.toList()));
		assertEquals(2,service.getUsers(2).size());
//		assertEquals(1,1);
	}

	@Test
	public void createUserTestInDb1(){
		Model1 obj=new Model1("RSD12333","Roshan","Roshan Singh Dhek", "Kondapur",
				"6393578389","Albanero","");
		when(db1.save(obj)).thenReturn(obj);
//		obj.setDataBase("Main-DataBase");
		assertEquals(obj,service.createUser(1,obj));
	}

	@Test
	public void deleteUserTest(){
		Model1 obj=new Model1("RSD12333","Roshan","Roshan Singh Dhek", "Kondapur",
				"6393578389","Albanero","");
//		db1.save(obj);
//		System.out.println(db1.findAll());
		service.deleteByEmpId("Sagar");
		assertEquals("SuccessFully Deleted",service.deleteByEmpId("Roshan"));
	}

	@Test
	public void putUserTest(){
		Model1 obj=new Model1("RSD12320","Vishal","Vishal Singh Dhek", "Kondapur",
				"6393578389","Albanero","backupDataBase");
		when(db1.findByUserName("Vishal")).thenReturn(List.of(obj));
		when(db1.save(obj)).thenReturn(obj);
		Model1 updatedUser = service.update("Vishal");
		System.out.println(updatedUser);
		assertEquals(obj,updatedUser);
	}

}
