package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.UserAnswer;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.repository.UserAnswerDao;
import com.example.demo.login.domain.repository.UserConsultationDao;

@Service
public class UserAnswerService {
	
	@Autowired
	UserAnswerDao dao;
	
	 /**
     * insert用メソッド.
     */
    public boolean insert(UserAnswer userAnswer) {

        // insert実行
        int rowNumber = dao.insertOne(userAnswer);

        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // insert成功
            result = true;
        }

        return result;
    }
    
    
    
    public List<UserAnswer> getOne(UserAnswer userAnswer) {
		return dao.getAll(userAnswer);
	}
	

}
