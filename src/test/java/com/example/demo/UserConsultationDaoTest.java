package com.example.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.repository.UserConsultationDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserConsultationDaoTest {
	
	@Autowired
	@Qualifier("UserConsultationDaoJdbcImpl")
	UserConsultationDao dao;//カウントメソッドのテスト1
	
	JdbcTemplate jdbc;
	
	
	@Test
	public void countTest1(){
		UserConsultation userConsultation = new UserConsultation();
		
		userConsultation.setTitle("熱があります");
		userConsultation.setContent("病院に行った方が良いでしょうか");
	//カウントメソッドの結果が1件であることをテスト
		assertEquals(dao.count(userConsultation),1);}
	

	//カウントメソッドのテスト2
	//ポイント：@Sql
	@Test
	@Sql("/testdata.sql")
	public void countTest2(){
		UserConsultation userConsultation = new UserConsultation();
		
		userConsultation.setTitle("熱があります");
		userConsultation.setContent("病院に行った方が良いでしょうか");
		//カウントメソッドの結果が2件であることをテスト
		assertEquals(dao.count(userConsultation),2);}
	
	
	@Test
	@Sql("/testdata.sql")
	public void insertOneTest(){
		UserConsultation userConsultation = new UserConsultation();
		
		userConsultation.setUserName("福井隼");
		userConsultation.setTitle("熱があります");
		userConsultation.setContent("病院に行った方が良いでしょうか");

		equals(dao.insertOne(userConsultation));}
	
	
	@Test
	@Sql("/testdata.sql")
	public void getAllTest(){
		
		String sql = "SELECT * FROM consultations";
		List<Map<String,Object>> resultList = jdbc.queryForList(sql);
		List<UserConsultation> list = new ArrayList<UserConsultation>();
		for(Map<String,Object> result : resultList) {
			UserConsultation userConsultation = new UserConsultation();
			userConsultation.setUserName((String)result.get("user_name"));;
			userConsultation.setTitle((String)result.get("title"));;
			userConsultation.setContent((String)result.get("content"));;
			list.add(userConsultation);
		}
		equals(dao.getAll());}
	}

