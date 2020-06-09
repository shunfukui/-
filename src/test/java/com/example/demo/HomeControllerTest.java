package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



import java.util.List;

import org.junit.Before;
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
    UserService userService;

    @Before
    public void setUp() throws Exception {
        //@sqlアノテーションで指定したsqlファイルによってセットアップを実行するため、処理なし
    }
    

    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
//    @Test
//    @Sql("/testdata.sql")
//    @WithMockUser
//    public void getUserDetailTest() throws Exception {
//    	
//    	String userName = "ddd";
//    	
//  
//    	// ユーザー情報を取得
//        User user = userService.selectOne(userName);
//        // SignupForm型のformをインスタンス生成
//        SignupForm form = new SignupForm();
//        
//        // Userクラスをフォームクラスに詰め替え
//        form.setUserName(user.getUserName()); //ユーザー名
//        form.setSex(user.isSex()); //性別
//        form.setAge(user.getAge()); //年齢
//        form.setMailAddress(user.getMailAddress()); //メールアドレス
//        form.setPassword(user.getPassword()); //パスワード
//        form.setLicense(user.isLicense()); //資格有無
//        	
//        //GETリクエストを送る、画面を取得
//        mockMvc.perform(get("/userDetail/ddd"))
//      
//           //modelに正しい変数を詰められているか？
//           .andExpect(model().attribute("signupForm", form));
//        
//    }

    //@WithMockUserアノテーションをメソッドに付ければ、ログイン後の画面をテストできる。
    @Test
    @Sql("/testdata.sql")
    @WithMockUser
    public void postUserDetailDeleteTest() throws Exception {
    	
    	
    	String userName = "ddd";
    	// ユーザー情報を取得
        User user = userService.selectOne(userName);
        // SignupForm型のformをインスタンス生成
        SignupForm form = new SignupForm();
        form.setUserName(user.getUserName()); //ユーザー名
        form.setSex(user.isSex()); //性別
        form.setAge(user.getAge()); //年齢
        form.setMailAddress(user.getMailAddress()); //メールアドレス
        form.setPassword(user.getPassword()); //パスワード
        form.setLicense(user.isLicense()); //資格有無
   	
//    	userDetailにリクエストを実行.perform((post("/userDetail?delete"))
    	mockMvc.perform((MockMvcRequestBuilderUtils.postForm("/userDetail?delete", form))
    			//postの際はつける
    			.with(SecurityMockMvcRequestPostProcessors.csrf())
    			//引数のSignupForm型のformをセット
    			.requestAttr("form", form ))
    			//result,削除成功と言うキーバリューが渡せているかの検証
    			.andExpect(model().attribute("result", "削除成功"));
    					
    	User user2 = userService.selectOne(userName);
    	System.out.println(user2.getUserName());
    }
}
