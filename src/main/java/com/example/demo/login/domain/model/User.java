package com.example.demo.login.domain.model;

import java.util.Date;

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
}
