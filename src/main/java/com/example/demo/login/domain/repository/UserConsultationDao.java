package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;


import com.example.demo.login.domain.model.UserConsultation;

public interface UserConsultationDao {
	
	
	// consultationテーブルの全データを取得.
    public int insertOne(UserConsultation userConsultation) throws DataAccessException;
    
  



	List<UserConsultation> getAll();
    
    
    
}
