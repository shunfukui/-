package com.example.demo.login.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class AnswerForm {
	
	//必須入力
    @NotBlank
    private String userName; // 名前
    
  //必須入力
    @NotBlank
    private String title; // 回答
		
	//必須入力
	@NotBlank
	private String content; // 回答
	
	//必須入力
		@NotBlank
		private String answerContent; // 

}
