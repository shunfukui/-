package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.repository.UserConsultationDao;
import com.example.demo.login.domain.service.Autowired;
import com.example.demo.login.domain.service.Consultations;
import com.example.demo.login.domain.service.DataAccessException;

public class UserConsultationDaoJdbcImpl implements UserConsultationDao {
	
	@Autowired
	JdbcTemplatejdbc;
	
	//consultationsテーブルにデータを1件insert.
	@Override
	public int insertOne(Consultations consultations) throws DataAccessException{
		return 0;}
	
	//consultationsテーブルのデータを１件取得
	@Override
	public Consultations selectOne(String title) throws DataAccessException{
		return null;
	}

}
