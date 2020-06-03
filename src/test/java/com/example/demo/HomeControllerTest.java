package com.example.demo;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserAnswer;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.service.UserConsultationService;
import com.example.demo.login.domain.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvcアノテーションをクラスに付ければ、Springが用意するモックを使用できる。
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserConsultationService userConsultationService;
    @Autowired
    UserService userService;
    @MockBean
    private UserService service;
    @MockBean
    private UserConsultationService service1;
    
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void getHomeTest() throws Exception {
    	
    	// listを持ってくる
    	List<UserConsultation> list = userConsultationService.getAll();
    	
    	//GETリクエストを送る、ホーム画面を取得
        mockMvc.perform(get("/home"))
     
        	//modelに正しい変数を詰められているか？
            .andExpect(model().attribute("consultationsList", list));
    }
    
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void getMyPageTest() throws Exception {
    	
    	List<User> myPage = userService.selectMany();
    	
    	//GETリクエストを送る、画面を取得
        mockMvc.perform(get("/myPage"))
     
        	//modelに正しい変数を詰められているか？
            .andExpect(model().attribute("myPage", myPage));
    }
        
//    @Test
//    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
//    @WithMockUser
//    public void getUserDetailTest() throws Exception {
//    	
//    	String userName ;
//  
//    	// ユーザー情報を取得
//        User user = userService.selectOne(userName);
//        SignupForm form = new SignupForm();
//        
//        // Userクラスをフォームクラスに変換
//        form.setUserName(user.getUserName()); //ユーザー名
//        form.setSex(user.isSex()); //性別
//        form.setAge(user.getAge()); //年齢
//        form.setMailAddress(user.getMailAddress()); //メールアドレス
//        form.setPassword(user.getPassword()); //パスワード
//        form.setLicense(user.isLicense()); //資格有無
//        	
//        //GETリクエストを送る、画面を取得
//        mockMvc.perform(get("/userDetail/福井隼"))
//         
//           //modelに正しい変数を詰められているか？
//           .andExpect(model().attribute("signupForm", form));
//        
//    }
    
    
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void postUserDetailUpdateTest() throws Exception {
    	
    	//Userインスタンスの生成
        User user = new User();
        SignupForm form = new SignupForm();

        //フォームクラスをUserクラスに変換
        user.setUserName(form.getUserName()); //ユーザー名
        user.setSex(form.isSex()); //性別
        user.setAge(form.getAge()); //年齢
        user.setMailAddress(form.getMailAddress()); //メールアドレス
        user.setPassword(form.getPassword()); //パスワード
        user.setLicense(form.isLicense()); //資格有無
        
        
        //GETリクエストを送る、画面を取得
        mockMvc.perform(get("/userDetail"))
        	
        	.andExpect(model().attribute("result", "更新成功"));
    }
    
//    @Test
//    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
//    @WithMockUser
//    public void apostUserDetailUpdateTest() throws Exception {
//    	
//    	 boolean result = userService.deleteOne(form.getUserName());
//
//         if (result == false) {
//
//             model.addAttribute("result", "削除成功");
//
//         } else {
//
//             model.addAttribute("result", "削除失敗");
//
//         } 
//         
//
//    }
    
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void postLogoutTest() throws Exception {
    	
    	mockMvc.perform(post("/login"))
    		.andExpect(redirectedUrl("/login"));
    }
    
    @Test
    public void postLogoutErrorTest() throws Exception {
    	
    	mockMvc.perform(post("/login"))
    		.andExpect(redirectedUrl("/login"));
        	

    }
	
}
