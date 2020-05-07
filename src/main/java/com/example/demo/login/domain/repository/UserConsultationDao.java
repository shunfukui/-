package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserConsultationDao {
	
	
    // consultationsテーブルにデータを1件insert.
    public (Consultations consultations) throws DataAccessException;

    // consultationsテーブルのデータを１件取得
    public consultations selectOne(String title) throws DataAccessException;
    
    
    
    
    //カラムは全部受け取る、JDBCに絞り込みはさせる

}
