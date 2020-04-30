package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Userテーブルの件数を取得.
    @Override
    public int count() throws DataAccessException {

        // 全件取得してカウント
        int count = jdbc.queryForObject("SELECT COUNT(*) FROM users", Integer.class);

        return count;
    }

    // Userテーブルにデータを1件insert.
    @Override
    public int insertOne(User user) throws DataAccessException {

        //パスワード暗号化
        String password = passwordEncoder.encode(user.getPassword());

        //１件登録
        int rowNumber = jdbc.update("INSERT INTO users("
        		+ " user_name,"
        		+ "sex,"
        		+ " age,"
        		+ " mail_address,"
                + " password,"
                + " license,"
                + " role)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)",
                user.getUserName(),
                user.isSex(),
                user.getAge(),
                user.getMailAddress(),
                password,
                user.isLicense(),
                user.getRole());

        return rowNumber;
    }

    // Userテーブルのデータを１件取得
    @Override
    public User selectOne(String mailAddress) throws DataAccessException {

        // １件取得
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM users"
                + " WHERE mail_address = ?", mailAddress);

        // 結果返却用の変数
        User user = new User();

        // 取得したデータを結果返却用の変数にセットしていく
        user.setUserName((String) map.get("user_name")); //名前
        user.setSex((Boolean) map.get("sex")); //性別
        user.setAge((Integer) map.get("age")); //年齢
        user.setMailAddress((String) map.get("mail_address")); //メールアドレス
        user.setPassword((String) map.get("password")); //パスワード
        user.setLicense((Boolean) map.get("license")); //資格有無
        user.setRole((String) map.get("role")); //ロール

        return user;
    }

    // Userテーブルの全データを取得.
    @Override
    public List<User> selectMany() throws DataAccessException {

        // M_USERテーブルのデータを全件取得
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM users");

        // 結果返却用の変数
        List<User> userList = new ArrayList<>();

        // 取得したデータを結果返却用のListに格納していく
        for (Map<String, Object> map : getList) {

            //Userインスタンスの生成
            User user = new User();

            // Userインスタンスに取得したデータをセットする
            user.setUserName((String) map.get("user_name")); //ユーザー名
            user.setSex((Boolean) map.get("sex")); //性別
            user.setAge((Integer) map.get("age")); //年齢
            user.setMailAddress((String) map.get("mail_address")); //メールアドレス
            user.setPassword((String) map.get("password")); //パスワード
            user.setLicense((Boolean) map.get("license")); // 資格有無
            user.setRole((String) map.get("role")); //ロール

            //結果返却用のListに追加
            userList.add(user);
        }

        return userList;
    }

    // Userテーブルを１件更新.
    @Override
    public int updateOne(User user) throws DataAccessException {

        //パスワード暗号化
        String password = passwordEncoder.encode(user.getPassword());

        //１件更新
        int rowNumber = jdbc.update("UPDATE USES"
                + " SET"
                + " user_name = ?,"
                + " sex = ?,"
                + " age = ?,"
                + " password = ?,"
                + " license = ?"
                + " WHERE mail_address = ?",
                password,
                user.getUserName(),
                user.isSex(),
                user.getAge(),
                user.isLicense(),
                user.getMailAddress());

        //トランザクション確認のため、わざと例外をthrowする
        //        if (rowNumber > 0) {
        //            throw new DataAccessException("トランザクションテスト") {
        //            };
        //        }

        return rowNumber;
    }

    // Userテーブルを１件削除.
    @Override
    public int deleteOne(String mailAddress) throws DataAccessException {

        //１件削除
        int rowNumber = jdbc.update("DELETE FROM users WHERmail_address = ?", mailAddress);

        return rowNumber;
    }

    //SQL取得結果をサーバーにCSVで保存する
    @Override
    public void userCsvOut() throws DataAccessException {

        // M_USERテーブルのデータを全件取得するSQL
        String sql = "SELECT * FROM users";

        // ResultSetExtractorの生成
        UserRowCallbackHandler handler = new UserRowCallbackHandler();

        //SQL実行＆CSV出力
        jdbc.query(sql, handler);
    }
}
