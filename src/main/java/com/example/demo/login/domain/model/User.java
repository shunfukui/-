package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class User {

	private String userName; //名前
	private boolean sex; //性別
	private int age; //年齢
    private String mailAddress; //メールアドレス
    private String password; //パスワード
    private boolean license; //資格有無
    private String role; //ロール
    private boolean isDeleted; //資格有無
    
    public User(String userName,boolean sex,int age,String mailAddress,String password,boolean license,String role){
		this.userName = userName;
		this.sex = sex;
		this.age = age;
		this.mailAddress = mailAddress;
		this.password = password;
		this.license = license;
		this.role = role;
		this.isDeleted = false;
	}
    
	public User(String mailAddress) {
		
		this.mailAddress = mailAddress;
	}
	
	public User() {
		
	}
}
