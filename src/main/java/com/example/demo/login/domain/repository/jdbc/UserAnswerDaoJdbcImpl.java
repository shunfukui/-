package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.UserAnswer;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.repository.UserAnswerDao;

@Repository
public class UserAnswerDaoJdbcImpl implements UserAnswerDao {
	
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int insertOne(UserAnswer userAnswer) throws DataAccessException {
		 //１件登録
        int rowNumber = jdbc.update("INSERT INTO answers("
        		+ "user_name,"
        		+ "title,"
        		+ "content)"
                + " VALUES(?, ?, ?)",
                userAnswer.getUserName(),
                userAnswer.getTitle(),
                userAnswer.getAnswerContent());

        return rowNumber;
	}
	

	@Override
	public List<UserAnswer> getAll(UserAnswer userAnswer) {
		String sql = "SELECT content FROM answers  WHERE "
				+ " title = '"+userAnswer.getTitle()+"' AND "
				+ "user_name = '"+userAnswer.getUserName()+"'";
				
		 
		List<Map<String,Object>>  sqlList = jdbc.queryForList(sql);
		
		
		
		
		List<Map<String,Object>> resultList = jdbc.queryForList(sql);
		List<UserAnswer> list = new ArrayList<UserAnswer>();
		for(Map<String,Object> result : resultList) {
			UserAnswer userAnswer1 = new UserAnswer();
			userAnswer1.setAnswerContent((String)result.get("content"));;
			list.add(userAnswer1);
			
		}	

		return list;
		}

	}


