package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.UserAnswer;
import com.example.demo.login.domain.model.UserConsultation;

public interface UserAnswerDao {
	
	// consultationテーブルの全データを取得.
    public int insertOne(UserAnswer userAnswer) throws DataAccessException;
    
  



	List<UserAnswer> getOne(UserAnswer userAnswer);
    

}
