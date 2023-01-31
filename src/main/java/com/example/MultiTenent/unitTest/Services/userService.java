package com.example.MultiTenent.unitTest.Services;

import com.example.MultiTenent.unitTest.Models.Model1;
import com.example.MultiTenent.unitTest.Repositorys.db1Repository.db1Repository;
import com.example.MultiTenent.unitTest.Repositorys.db2Repository.db2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class userService {

    @Autowired
    private db1Repository db1;
    @Autowired
    private db2Repository db2;
    public Model1 createUser(int DBno , Model1 obj){
        Model1 obj1 = null;
        try {

            if (DBno == 0) {
                obj.setDataBase("Main-DataBase");
                obj1 = db1.save(obj);
                obj.setDataBase("Backup-DataBase");
                db2.save(obj);
            } else {
                obj.setDataBase("Main-DataBase");
                obj1 = db1.save(obj);
            }
        }catch (org.springframework.dao.DuplicateKeyException ex){
//            return ResponseEntity.status(HttpStatus.OK).body("Please provide unique userName");
        }
        return obj1;
    }

    public List<Model1>  getUsers(int DBno){
        List<Model1> obj1=null;
        if(DBno==1){
            obj1= db1.findAll();
        }else if(DBno==2){
            obj1= db2.findAll();
        }
        return obj1;
    }

    public Model1 update( String userName){
        System.out.println("hello");
        List<Model1> userOptional = db1.findByUserName(userName);
        String fullName = userOptional.get(0).getFullName();
        String newStr = "";
        String allSpecialChar="<>@!#$%^&*()_+[]{}?:|'/~`-=";
        int low = 0;
        int high = 26;
        for(int i=0;i<fullName.length();i++){
            char ele=fullName.charAt(i);
            Integer code=(int) ele;
            if(ele=='a'||ele=='e'||ele=='i'||ele=='o'||ele=='u'||ele=='A'||ele=='E'||ele=='I'||ele=='O'||ele=='U'){
                Random r = new Random();
                int randomNo = r.nextInt(high-low) + low;
                newStr=newStr+allSpecialChar.charAt(randomNo);
            }else{
                newStr=newStr+ele;
            }
        }
        userOptional.get(0).setFullName(newStr);
        Model1 savedData=db1.save(userOptional.get(0));
//        return userOptional.get(0);
        return savedData;
    }

    public String deleteByEmpId( String userName){
        db1.deleteByUserName(userName);
        return "SuccessFully Deleted";
    }
}
