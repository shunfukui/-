package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class UserConsultation {
	
	private String userName; //名前
	private String title; //題名
	private String content; //内容
	
	
	public UserConsultation(String title,String content){
		this.title = "熱があります";
		this.content = "病院に行った方が良いでしょうか";
	}


	public UserConsultation() {
		
	}
}


