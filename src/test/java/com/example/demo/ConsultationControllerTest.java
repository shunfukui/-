package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.security.Principal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import com.example.demo.login.domain.model.ConsultationForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.service.UserConsultationService;
import com.example.demo.login.domain.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvcアノテーションをクラスに付ければ、Springが用意するモックを使用できる。
@AutoConfigureMockMvc
public class ConsultationControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    UserService userService;
	
	@Autowired
    UserConsultationService userConsultationService;
	
//	@Test
//    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
//    @WithMockUser
//    public void getconsultationTest() throws Exception {
//		Principal principal,
//		
//		UserConsultation userConsultationName = new UserConsultation();
//		 principal.getName();
//		
//		 //ユーザー一覧の生成
//	     String myName = userService.selectName( principal.getName());
//		
//		ConsultationForm nameList2 = new ConsultationForm();
//		
//		 nameList2.setUserName(myName); //ユーザー名
//		
//		 
//		 mockMvc.perform(get("/consultation"))
//        
//        	//modelに正しい変数を詰められているか？
//            .andExpect(model().attribute("consultationForm", nameList2));
//		 	
//    }
	
	
	@Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void consultationTest() throws Exception {
		
		UserConsultation form = new UserConsultation();
		ConsultationForm consultationForm = new ConsultationForm();
		
		boolean userConsultation  = userConsultationService.insert(form);
		
		
        mockMvc.perform(get("/myPage"))
     
        	.andExpect(flash().attribute("userName", consultationForm.getUserName()));
    	
    	
    }

}
