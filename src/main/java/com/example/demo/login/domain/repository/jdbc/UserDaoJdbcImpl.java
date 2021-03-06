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
//    @Override
//    public int count() throws DataAccessException {
//
//        // 全件取得してカウント
//        int count = jdbc.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
//
//        return count;
//    }

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
    public User selectAll(String userName) throws DataAccessException {

        // １件取得
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM users"
                + " WHERE user_name = '"+userName+"'");

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

        // USERテーブルのデータを全件取得
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM users WHERE　NOT is_deleted = true");

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
        int rowNumber = jdbc.update("UPDATE USERS"
                + " SET"
                + " sex = ?,"
                + " age = ?,"
                + " password = ?,"
                + " mail_address = ?,"
                + " license = ?"
                + " WHERE user_name = ?",
                user.isSex(),
                user.getAge(),
                password,
                user.getMailAddress(),
                user.isLicense(),
                user.getUserName());
               
        return rowNumber;
    }

    // Userテーブルを１件削除.
    @Override
    public int deleteOne(String userName) throws DataAccessException {

        
    	//UPDATE 更新したいテーブル名
    	//WHERE 抽出したい行に関する条件 user_nameとis_deletedがfalse
    	//SET 更新する列名と値を記述 false（削除されていない状態)をtrue(削除された状態)に更新する
    	 int rowNumber = jdbc.update("UPDATE users "
    	        	+ " SET is_deleted = ?"
    	        	+ " WHERE user_name = ?", true, userName);

        return rowNumber;
       
    }


	@Override
	public String selectName(String mailAddress) throws DataAccessException {
		// １件取得
        String map = jdbc.queryForObject("SELECT user_name FROM users"
                + " WHERE mail_address = '"+mailAddress+"'",String.class);
        
        return map;
	}
}
