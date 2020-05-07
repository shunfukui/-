package com.example.demo.login.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ConsultationForm {
	//必須入力
   @NotBlank
    @Size(min = 1,max = 50,message = "50文字以内でお願いします")
    private String title; // 題名
	
	//必須入力
    @NotBlank
    private String content; // 内容

}
