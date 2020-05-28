package com.example.demo;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
import com.example.demo.login.domain.repository.UserAnswerDao;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserAnswerDaoTest {
	@Autowired
	@Qualifier("UserAnswerDaoJdbcImpl")
	UserAnswerDao dao;
	
	JdbcTemplate jdbc;
	
	@Test
	@Sql("/testdata.sql")
	public void insertOneTest(){
		UserAnswer userAnswer = new UserAnswer();
		
		userAnswer.setUserName("福井隼");
		userAnswer.setTitle("熱があります");
		userAnswer.setAnswerContent("病院に行き、薬を飲み、安静にしていましょう");
		
		assertEquals(dao.insertOne(userAnswer),1);
	}
	
	@Test
	@Sql("/testdata.sql")
	public void getAllTest(){
		UserAnswer userAnswer = new UserAnswer();
		
		userAnswer.setUserName("福井隼");
		userAnswer.setTitle("熱があります");
		List<UserAnswer> list = dao.getAll(userAnswer);
		
		//Test equals
        assertThat(list, hasItems(
        	new UserAnswer(null,null,"病院に行き、薬を飲み、安静にしていましょう")
                 
        ));
	
		}
	}


