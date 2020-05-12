package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper {
	

    // 登録用メソッド
    @Insert("INSERT INTO users ("
            + " user_name,"
            + " sex,"
            + " age,"
            + " mailaddress,"
            + " password,"
            + " license,"
            + " role)"
            + " VALUES ("
            + " #{userName},"
            + " #{sex},"
            + " #{age},"
            + " #{mailaddress},"
            + " #{password},"
            + " #{license},"
            + " #{role})")
    public boolean insert(User user);

    // １件検索用メソッド
    @Select("SELECT user_name AS userName,"
            + "password,"
            + "sex,"
            + "mailaddress,"
            + "age,"
            + "license,"
            + "role"
            + " FROM users"
            + " WHERE user_name = #{userName}")
    public User selectOne(String userName);

    // 全件検索用メソッド
    @Select("SELECT user_name AS userName,"
            + "password,"
            + "sex,"
            + "mailaddress,"
            + "age,"
            + "license,"
            + "role"
            + " FROM users")
    public List<User> selectMany();

    // １件更新用メソッド
    @Update("UPDATE users SET"
            + " password = #{password},"
            + " user_name = #{userName},"
            + " license = #{license},"
            + " age = #{age},"
            + " sex = #{sex}"
            + " WHERE user_name = #{userName}")
    public boolean updateOne(User user);

    // １件削除用メソッド
    @Delete("DELETE FROM users WHERE user_name = #{userName}")
    public boolean deleteOne(String userName);
}