package com.example.demo;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.List;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.domain.model.AnswerForm;
import com.example.demo.login.domain.model.UserAnswer;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.service.UserAnswerService;
import com.example.demo.login.domain.service.UserConsultationService;



@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvcアノテーションをクラスに付ければ、Springが用意するモックを使用できる。
@AutoConfigureMockMvc
public class AnswerControllerTest {
	@Autowired
    private MockMvc mockMvc;
	@Autowired
	   UserAnswerService userAnswerService;
	 @Autowired
	   UserConsultationService userConsultationService;
	 

	
	
	@Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void getanswerTest() throws Exception {
		
		UserAnswer userAnswer = new UserAnswer();
		
		// listを持ってくる
		List<UserAnswer> list = userAnswerService.getOne(userAnswer);
    	
    	
    	//GETリクエストを送る、ホーム画面を取得
        mockMvc.perform(get("/answer"))
     
        	//modelに正しい変数を詰められているか？
            .andExpect(model().attribute("AnswerList", list));
    }
	
	@Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void postanswerTest() throws Exception {
		
		//必要な情報をフィールドとして設定
		String form1 =	"名前";
		String form2 = 	"タイトル";
		String form3 = 	"内容";
		
		
		//answerFormをインスタンス化して回答（answerContent）を設定する
		AnswerForm answerForm = new AnswerForm();
		answerForm.setUserName("福井隼");
		answerForm.setTitle("熱があります");
		answerForm.setContent("病院に行った方が良いでしょうか");
		answerForm.setAnswerContent("病院に行き、薬を飲み、安静にしていましょう");
		
	    mockMvc.perform((post("/answer"))
	    		.with(SecurityMockMvcRequestPostProcessors.csrf())
	    		.flashAttr("answerForm", answerForm)
	    		.param("form1", form1)
	    		.param("form2", form2)
	    		.param("form3", form3))
	    		
				.andExpect(model().hasNoErrors())
				
				.andExpect(flash().attribute("userName", is(answerForm.getUserName())))
				.andExpect(flash().attribute("title", is(answerForm.getTitle())))
				.andExpect(flash().attribute("content", is(answerForm.getContent())))
				.andExpect(flash().attribute("answerContent", is(answerForm.getAnswerContent())));
		       
    }
	
	@Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void postanswerErrorTest() throws Exception {
		
		//必要な情報をフィールドとして設定
		String form1 =	"名前";
		String form2 = 	"タイトル";
		String form3 = 	"内容";
		
		UserAnswer userAnswer = new UserAnswer();
		UserAnswer form = new UserAnswer();
		AnswerForm answerForm = new AnswerForm();
		 
		UserConsultation list1 = new UserConsultation();
		list1.setUserName(form1); //ユーザー名
        list1.setTitle(form2); //タイトル
        list1.setContent(form3); //内容
        
        
        List<UserAnswer> list = userAnswerService.getOne(form);
		
		mockMvc.perform((post("/answer"))
		    		.with(SecurityMockMvcRequestPostProcessors.csrf())
		    		.flashAttr("answerForm", answerForm)
		    		.param("form1", form1)
		    		.param("form2", form2)
		    		.param("form3", form3))
		    		
					.andExpect(model().hasErrors())
					
					.andExpect(model().attribute("AnswerList", list));
	}
}
