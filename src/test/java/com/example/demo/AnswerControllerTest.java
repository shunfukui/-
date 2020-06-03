package com.example.demo;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.web.bind.annotation.ModelAttribute;

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
		
		//変数list1に設定したフィールドの値を詰める
		UserConsultation list1 = new UserConsultation();
		list1.setUserName(form1); //ユーザー名
		list1.setTitle(form2); //タイトル
		list1.setContent(form3); //内容
		
		//answerFormをインスタンス化して回答（answerContent）を設定する
		AnswerForm answerForm = new AnswerForm();
		answerForm.setAnswerContent("あああ");
		
		
	    mockMvc.perform((post("/answer"))
	    		.with(SecurityMockMvcRequestPostProcessors.csrf())
	    		.flashAttr("answerForm", answerForm)
	    		.param("form1", form1)
	    		.param("form2", form2)
	    		.param("form3", form3))
	    		.andExpect(status().isOk())
				.andExpect(model().hasNoErrors()
				);
//			.andExpect(model().hasNoErrors());
//		    .andExpect(model().attribute("ConsultationList", list1)) 
//	        .andExpect(model().attribute("answerForm", answerForm))    	
//	        .andExpect(view().name("answer"));
    }
	
	@Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void postanswerErrorTest() throws Exception {
		
		AnswerForm answerForm = new AnswerForm();
		answerForm.setAnswerContent("");
		
	    mockMvc.perform((post("/answer")).flashAttr("answerForm", answerForm))
		    .andExpect(model().hasErrors())
	        .andExpect(model().attribute("answerForm", answerForm))    	
	        .andExpect(view().name("answer"));
    }
}
