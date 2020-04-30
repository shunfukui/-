package com.example.demo.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.login.domain.model.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        //戻り値用のUserインスタンスを生成
        User user = new User();
        
        //ResultSetの取得結果をUserインスタンスにセット
        user.setUserName(rs.getString("user_name"));
        user.setSex(rs.getBoolean("sex"));
        user.setAge(rs.getInt("age"));
        user.setMailAddress(rs.getString("mail_address"));
        user.setPassword(rs.getString("password"));
        user.setLicense(rs.getBoolean("license"));
        user.setRole(rs.getString("role"));

        return user;
    }
}
