package com.example.demo;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserAnswer;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.repository.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;
    
    JdbcTemplate jdbc;

    
    @Test
	@Sql("/testdata.sql")
	public void insertOneTest(){
		User user = new User();
		
		user.setUserName("bbb");
		user.setSex(true);
		user.setAge(29);
		user.setMailAddress("shun@gmail.com");
		user.setPassword("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa");
		user.setLicense(true);
		user.setRole("ROLE_GENERAL");
	   
		assertEquals(dao.insertOne(user),1);
		}
    
    
    @Test
	@Sql("/testdata.sql")
	public void selectAllTest(){
		User user = new User();
		String userName ;
		
		user.setUserName("福井隼");
		
		User list1 = dao.selectAll(user.getUserName());
		
		//Test equals
        assertThat(list1, is(
        new User("福井隼",true,29,"shun@gmail.com","$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa",true,"ROLE_GENERAL")  
        ));
    }
	
    @Test
	@Sql("/testdata.sql")
	public void updateOneTest(){
		User user = new User();
				
		user.setUserName("福井隼");
		user.setSex(true);
		user.setAge(29);
		user.setMailAddress("shun@gmail.com");
		user.setPassword("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa");
		user.setLicense(true);
		user.setRole("ROLE_GENERAL");
	   
		assertEquals(dao.updateOne(user),1);
		}
    
    @Test
	@Sql("/testdata.sql")
	public void deleteOneTest(){
    	User user = new User();
    	
		user.setUserName("福井隼");

		assertEquals(dao.deleteOne(user.getUserName()),1);
	}
    
    @Test
	@Sql("/testdata.sql")
	public void selectNameTest(){
		
		String mailAddress = "shun@gmail.com";
		
		String name = dao.selectName(mailAddress);
		
		//Test equals
		//String型はインスタンス化する必要ない
		assertThat(name, is("福井隼"));
		//	型にあった変数名、
	}
}
