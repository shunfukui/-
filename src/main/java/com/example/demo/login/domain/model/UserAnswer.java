package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class UserAnswer {
	
	private String userName; //回答
	private String title; //回答
	private String answerContent; //回答
	
//	public UserAnswer(String userName,String title,String answerContent){
//		this.userName = "福井隼";
//		this.title = "熱があります";
//		this.answerContent = "病院に行き、薬を飲み、安静にしていましょう";
//	}
	
	public UserAnswer(String userName,String title,String answerContent){
		this.userName = userName;
		this.title = title;
		this.answerContent = answerContent;
	}


	public UserAnswer() {
		
	}

}
