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

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

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
    private UserConsultationService service1;
    //メソッドごとにつけるアノテーションではなく、クラス単位で1つ
    
   
    
    
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
        form.setUserName(user.getUserName());
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
    
    
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @Test
    @Sql("/testdata.sql")
    @WithMockUser
    public void postUserDetailUpdateTest() throws Exception {
    	
    	// SignupForm型のformをインスタンス生成
        SignupForm form = new SignupForm();
        form.setUserName("ddd");
        form.setSex(true);
        form.setAge(29);
        form.setMailAddress("shun@gmail.com");
        form.setPassword("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa");
        form.setLicense(true);
        
        //Userインスタンスの生成
        User user = new User();
        
        //user型にform型を詰め替える
        user.setUserName(form.getUserName()); //ユーザー名
        user.setSex(form.isSex()); //性別
        user.setAge(form.getAge()); //年齢
        user.setMailAddress(form.getMailAddress()); //メールアドレス
        user.setPassword(form.getPassword()); //パスワード
        user.setLicense(form.isLicense()); //資格有無
        
        
        boolean result = userService.updateOne(user);
        
        //userDetailにリクエストを実行.perform((post("/userDetail?delete"))
    	mockMvc.perform((MockMvcRequestBuilderUtils.postForm("/userDetail?update", user))
    			//postの際はつける
    			.with(SecurityMockMvcRequestPostProcessors.csrf()))
    			
    			//result,削除成功と言うキーバリューが渡せているかの検証
    			.andExpect(model().attribute("result", "更新成功"));
    }
    
    
    
  //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @Test
    @WithMockUser
    public void postUserDetailUpdateErrorTest() throws Exception {
    	
    	// SignupForm型のformをインスタンス生成
        SignupForm form = new SignupForm();
        form.setUserName("fef");
        form.setSex(true);
        form.setAge(30);
        form.setMailAddress("fff@gmail.com");
        form.setPassword("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa");
        form.setLicense(true);
       
        //Userインスタンスの生成
        User user = new User();
        
        //user型にform型を詰め替える
        user.setUserName(form.getUserName()); //ユーザー名
        user.setSex(form.isSex()); //性別
        user.setAge(form.getAge()); //年齢
        user.setMailAddress(form.getMailAddress()); //メールアドレス
        user.setPassword(form.getPassword()); //パスワード
        user.setLicense(form.isLicense()); //資格有無
        
        
        boolean result = userService.updateOne(user);
        
        //userDetailにリクエストを実行.perform((post("/userDetail?delete"))
    	mockMvc.perform((MockMvcRequestBuilderUtils.postForm("/userDetail?update", form))
    			//postの際はつける
    			.with(SecurityMockMvcRequestPostProcessors.csrf()))
    			
    			//result,削除失敗と言うキーバリューが渡せているかの検証
    			.andExpect(model().attribute("result", "更新失敗(トランザクションテスト)"));
    	
    }
    
    
    

    
    
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @Test
    @WithMockUser
    public void postUserDetailDeleteTest() throws Exception {
    
    	String userName = "ddd";
    	// ユーザー情報を取得
        User user = userService.selectOne(userName);
        // SignupForm型のformをインスタンス生成
        SignupForm form = new SignupForm();
        //フォームクラスをUserクラスに変換
        form.setUserName(user.getUserName()); //ユーザー名
        form.setSex(user.isSex()); //性別
        form.setAge(user.getAge()); //年齢
        form.setMailAddress(user.getMailAddress()); //メールアドレス
        form.setPassword(user.getPassword()); //パスワード
        form.setLicense(user.isLicense()); //資格有無
       
        //userDetailにリクエストを実行.perform((post("/userDetail?delete"))
    	mockMvc.perform((MockMvcRequestBuilderUtils.postForm("/userDetail?delete", form))
    	
    			//postの際はつける
    			.with(SecurityMockMvcRequestPostProcessors.csrf()))
    			
    			//result,削除成功と言うキーバリューが渡せているかの検証
    			.andExpect(model().attribute("result", "削除成功"));
    					
    	User user2 = userService.selectOne(userName);
    	System.out.println(user2.getUserName());
    }
    
    
    
    @Test
    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @WithMockUser
    public void postUserDetailDeleteErrorTest() throws Exception {
    	
    	//登録してない名前を代入
    	String userName = "aaa";
    	// ユーザー情報を取得
        User user = userService.selectOne(userName);
        // SignupForm型のformをインスタンス生成
        SignupForm form = new SignupForm();
        //フォームクラスをUserクラスに変換
        form.setUserName(user.getUserName()); //ユーザー名
        form.setSex(user.isSex()); //性別
        form.setAge(user.getAge()); //年齢
        form.setMailAddress(user.getMailAddress()); //メールアドレス
        form.setPassword(user.getPassword()); //パスワード
        form.setLicense(user.isLicense()); //資格有無
       
        //userDetailにリクエストを実行.perform((post("/userDetail?delete"))
    	mockMvc.perform((MockMvcRequestBuilderUtils.postForm("/userDetail?delete", form))
    	
    			//postの際はつける
    			.with(SecurityMockMvcRequestPostProcessors.csrf()))
    			
    			//result,削除成功と言うキーバリューが渡せているかの検証
    			.andExpect(model().attribute("result", "削除成功"));
    	
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
    		.andExpect(status().is(302));
    		

    }
}
