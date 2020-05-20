package com.example.demo.login.domain.repository.jdbc;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.repository.UserConsultationDao;

//import com.example.demo.login.domain.service.Consultations;

@Repository
public class UserConsultationDaoJdbcImpl implements UserConsultationDao {
	
	@Autowired
	JdbcTemplate jdbc;
	
	
	// Userテーブルの件数を取得.
    @Override
    public int count(UserConsultation userConsultation) throws DataAccessException {

        // 全件取得してカウント
        int count = jdbc.queryForObject("SELECT COUNT(*) FROM consultations WHERE "
        		+ " title = '"+userConsultation.getTitle()+"' AND "
        		+ "content = '"+userConsultation.getContent()+"'",
                
                
                
         Integer.class);

        return count;
    }
	

	// テーブルにデータを1件insert.
    @Override
    public int insertOne(UserConsultation userConsultation) throws DataAccessException{


        //１件登録
        int rowNumber = jdbc.update("INSERT INTO consultations("
        		+ " user_name,"
        		+ " title,"
        		+ "content)"
                + " VALUES(?, ? ,?)",
                userConsultation.getUserName(),
                userConsultation.getTitle(),
                userConsultation.getContent());

        return rowNumber;
    }
	
    @Override
    public List<UserConsultation> getAll() {
		String sql = "SELECT * FROM consultations";
				List<Map<String,Object>> resultList = jdbc.queryForList(sql);
				List<UserConsultation> list = new ArrayList<UserConsultation>();
				for(Map<String,Object> result : resultList) {
					UserConsultation userConsultation = new UserConsultation();
					userConsultation.setUserName((String)result.get("user_name"));;
					userConsultation.setTitle((String)result.get("title"));;
					userConsultation.setContent((String)result.get("content"));;
					list.add(userConsultation);
					
					System.out.println(result.get("title"));
				}	

				return list;
				}

	@Override
	public UserConsultation getOne(UserConsultation userconsultation) {
		String sql = "SELECT title,content FROM consultations";
//				+ " user_name = '"+userconsultation.getUserName()+"' AND "
//				+ " title = '"+userconsultation.getTitle()+"' AND "
//        		+ "content = '"+userconsultation.getContent()+"'";
				
              

		List<Map<String,Object>> resultList = jdbc.queryForList(sql);
		List<UserConsultation> list1 = new ArrayList<UserConsultation>();
		for(Map<String,Object> result1 : resultList) {
		
			
		//SQLから取ってきた値を型変換
			UserConsultation userConsultation = new UserConsultation();
			userConsultation.setTitle((String)result1.get("title"));;
			userConsultation.setContent((String)result1.get("content"));;
			
			
			System.out.println(result1.get("title"));	
		}

		return userconsultation;
	}


	
}


