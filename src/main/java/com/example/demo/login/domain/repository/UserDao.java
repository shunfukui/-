package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {

//    // Userテーブルの件数を取得.
//    public int count() throws DataAccessException;

    // Userテーブルにデータを1件insert.
    public int insertOne(User user) throws DataAccessException;

    // Userテーブルのデータを１件取得
    public User selectAll(String userName) throws DataAccessException;
    
 // Userテーブルのデータを１件取得
    public String selectName(String mailAddress) throws DataAccessException;

    // Userテーブルの全データを取得.
    public List<User> selectMany() throws DataAccessException;

    // Userテーブルを１件更新.
    public int updateOne(User user) throws DataAccessException;

    // Userテーブルを１件削除.
    public int deleteOne(String user) throws DataAccessException;

//    //SQL取得結果をサーバーにCSVで保存する
//    public void userCsvOut() throws DataAccessException;
}
