package com.example.demo.login.domain.repository.jdbc;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.repository.UserConsultationDao;

//import com.example.demo.login.domain.service.Consultations;

@Repository
public class UserConsultationDaoJdbcImpl implements UserConsultationDao {
	
	@Autowired
	JdbcTemplate jdbc;
	
	//consultationsテーブルにデータを1件insert.
	@Override
	public int insertOne(UserConsultation userConsultation) throws DataAccessException{
		return 0;}
	
	
	
	public List<UserConsultation> gettAll() {
		String sql = "SELECT title,content FROM consultations";
				List<Map<String,Object>> resultList = jdbc.queryForList(sql);
				List<UserConsultation> list = new ArrayList<UserConsultation>();
				for(Map<String,Object> result : resultList) {
					UserConsultation userConsultation = new UserConsultation();
					userConsultation.setTitle((String)result.get("title"));;
					userConsultation.setContent((String)result.get("content"));;
					list.add(userConsultation);
				}	
				return list;
				}
}
