package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    //Userテーブルの件数を取得.
    @Override
    public int count() {

        //SQL分
        String sql = "SELECT COUNT(*) FROM users";

        //パラメーター生成
        SqlParameterSource params = new MapSqlParameterSource();

        //全件取得してカウント
        return jdbc.queryForObject(sql, params, Integer.class);
    }

    //Userテーブルにデータを1件insert.
    @Override
    public int insertOne(User user) {

        //SQL文
        String sql = "INSERT INTO users("
        		+   " user_name,"
        		+ 	"sex,"
        		+   " age,"
        		+   " mailaddress,"
                +   " password,"
                +   " license,"
                +   " role)"
                + " VALUES(:mailAddress,"
                +   " :password,"
                +   " :userName,"
                +   " :sex,"
                +   " :age,"
                +   " :license,"
                +   " :role)";

        //パラメーター
        SqlParameterSource params = new MapSqlParameterSource()
        		.addValue("userName", user.getUserName())
        		.addValue("sex", user.isSex())
        		.addValue("age", user.getAge())
                .addValue("mailAddress", user.getMailAddress())
                .addValue("password", user.getPassword())
                .addValue("license", user.isLicense())
                .addValue("role", user.getRole());

        //SQL実行
        return jdbc.update(sql, params);
    }

    //Userテーブルのデータを１件取得
    @Override
    public User selectOne(String mailAddress) {

        //SQL文
        String sql = "SELECT * FROM users WHERE mail_address = :mailAddress";

        //パラメーター
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress);

        //SQL実行
        Map<String, Object> map = jdbc.queryForMap(sql, params);

        //結果返却用のインスタンスを生成
        User user = new User();

        //取得データをインスタンスにセットしていく
        user.setUserName((String)map.get("user_name")); //ユーザー名
        user.setSex((Boolean)map.get("sex"));    //性別
        user.setAge((Integer)map.get("age"));           //年齢
        user.setMailAddress((String)map.get("mail_address"));    //めーるあどれす
        user.setPassword((String)map.get("password"));  //パスワード
        user.setLicense((Boolean)map.get("license")); //資格有無
        user.setRole((String)map.get("role")); //ロール

        return user;
    }

    //Userテーブルの全データを取得.
    @Override
    public List<User> selectMany() {

        //SQL文
        String sql = "SELECT * FROM m_user";

        //パラメーター
        SqlParameterSource params = new MapSqlParameterSource();

        //SQL実行
        List<Map<String, Object>> getList = jdbc.queryForList(sql, params);

        //結果返却用のList
        List<User> userList = new ArrayList<>();

        //取得データ分loop
        for(Map<String, Object> map: getList) {

            //Userインスタンスの生成
            User user = new User();

            //Userインスタンスに取得したデータをセットする
            user.setUserName((String)map.get("user_name")); //名前
            user.setSex((Boolean)map.get("sex"));    //性別
            user.setAge((Integer)map.get("age"));           //年齢
            user.setMailAddress((String)map.get("mail_address"));    //メールアドレス
            user.setPassword((String)map.get("password"));  //パスワード
            user.setLicense((Boolean)map.get("license")); //資格有無
            user.setRole((String)map.get("role")); //ロール

            //Listに追加
            userList.add(user);
        }

        return userList;
    }

    //Userテーブルを１件更新.
    @Override
    public int updateOne(User user) {

        //SQL文
        String sql = "UPDATE USERS"
                + " SET"
                +   " password = :password,"
                +   " user_name = :userName,"
                +   " sex = :sex,"
                +   " age = :age,"
                +   " license = :license"
                + " WHERE mail_address = :mailAddress";

        //パラメーター
        SqlParameterSource params = new MapSqlParameterSource()
        		.addValue("userName", user.getUserName())
        		.addValue("sex", user.isSex())
        		.addValue("age", user.getAge())
                .addValue("password", user.getPassword())
                .addValue("mailAddress", user.getMailAddress())
                .addValue("license", user.isLicense());

        //SQL実行
        return jdbc.update(sql, params);
    }

    //Userテーブルを１件削除.
    @Override
    public int deleteOne(String mailAddress) {

        //SQL文
        String sql = "DELETE FROM users WHERE mail_address = :mailAddress";

        //パラメーター
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress);

        //SQL実行
        int rowNumber = jdbc.update(sql, params);

        return rowNumber;
    }

    //SQL取得結果をサーバーにCSVで保存する
    @Override
    public void userCsvOut() {

        //M_USERテーブルのデータを全件取得するSQL
        String sql = "SELECT * FROM users";

        //ResultSetExtractorの生成
        UserRowCallbackHandler handler = new UserRowCallbackHandler();

        //クエリー実行＆CSV出力
        jdbc.query(sql, handler);
    }
}