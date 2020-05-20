package com.example.demo.login.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.repository.UserConsultationDao;

@Service
public class UserConsultationService {
	

	
	@Autowired
	UserConsultationDao dao;
	
	/**
     * カウント用メソッド.
     */
    public boolean count(UserConsultation userConsultatio) {
    	
    	if(dao.count(userConsultatio) == 0) {
    		return true;
    	}else{
    		return false;
    	}
        
    }
    
    

	
	
	 /**
     * insert用メソッド.
     */
    public boolean insert(UserConsultation userConsultation) {
    	
    	

        // insert実行
        int rowNumber = dao.insertOne(userConsultation);

        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // insert成功
            result = true;
        }

        return result;
    }
    
    
    
    public List<UserConsultation> getAll() {
		return dao.getAll();
	}
    
    public UserConsultation getOne(UserConsultation userConsultation) {
		return dao.getOne(userConsultation);
	}
	
	
	
}
