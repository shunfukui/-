package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;




import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.repository.UserDao;
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
        
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void getUserDetailTest() throws Exception {
    	
    	String userName = "福井隼";
    	
  
    	// ユーザー情報を取得
        User user = userService.selectOne(userName);
        // SignupForm型のformをインスタンス生成
        SignupForm form = new SignupForm();
        
        // Userクラスをフォームクラスに詰め替え
        form.setUserName(user.getUserName()); //ユーザー名
        form.setSex(user.isSex()); //性別
        form.setAge(user.getAge()); //年齢
        form.setMailAddress(user.getMailAddress()); //メールアドレス
        form.setPassword(user.getPassword()); //パスワード
        form.setLicense(user.isLicense()); //資格有無
        	
        //GETリクエストを送る、画面を取得
        mockMvc.perform(get("/userDetail/福井隼"))
      
           //modelに正しい変数を詰められているか？
           .andExpect(model().attribute("signupForm", form));
        
    }
    
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    @Sql("/testdata.sql")
    public void postUserDetailDeleteTest() throws Exception {
    	
    	
    	//SingnupForm型の変数formをインスタンス生成
    	SignupForm form = new SignupForm();
    	form.setUserName("aaa");
    	
    	
    	
    	 boolean service = userService.deleteOne("aaa");
    	 
    	 assertEquals(service,true);
    	//userDetailにリクエストを実行
//    	mockMvc.perform((post("/userDetail?delete="))
//    			
//    			//postの際はつける
//    			.with(SecurityMockMvcRequestPostProcessors.csrf())
//    			
//    			//引数のSignupForm型のformをセット
//    			.flashAttr("form", form ))
//    			
//    			//result,削除成功と言うキーバリューが渡せているかの検証
//    			.andExpect(model().attribute("result", "削除成功"));
    }
    
    
    
    
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void postUserDetailDeleteErrorTest() throws Exception {
    	
    	//SingnupForm型の変数formをインスタンス生成
    	SignupForm form = new SignupForm();
    	form.setUserName("福井隼");

    	//boolean型の変数resultにfalseを代入
    	boolean result = false;   
    	
    	//userDetailにリクエストを実行
    	mockMvc.perform((post("/userDetail?delete="))
    			
    			//postの際はつける
    			.with(SecurityMockMvcRequestPostProcessors.csrf())
    	
    			//引数のSignupForm型のformをセット
    			.flashAttr("form", form ))
    			
    			//result,削除成功と言うキーバリューが渡せているかの検証
    			.andExpect(model().attribute("result", "削除失敗"));
    }
    
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void postLogoutTest() throws Exception {
    	
    	//logoutにリクエストを実行
    	mockMvc.perform((post("/logout"))
    		
    		//postの際はつける
    		.with(SecurityMockMvcRequestPostProcessors.csrf()))
	       
		    //loginにリダイレクトできているか確認
    		.andExpect(redirectedUrl("/login"));
	       		
    }
    
    @Test
    public void postLogoutErrorTest() throws Exception {
    	//logoutにリクエストを実行
    	mockMvc.perform((post("/logout"))

    		//postの際はつける
        	.with(SecurityMockMvcRequestPostProcessors.csrf()))
    		//ページを転送処理がしっかり行われているかをコード
    		.andExpect(status().is(302))
    		// /loguotでlogin.htmlを返すかを確認するコード
    		.andExpect(view().name("login"));

    }
	
}
