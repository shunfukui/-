package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.service.UserConsultationService;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    UserConsultationService userConsultationService;
    
    
    

    // 結婚ステータスのラジオボタン用変数
    private Map<String, String> radioLicense;
    private Map<String, String> radioSex;

    /**
     * ラジオボタンの初期化メソッド（ユーザー登録画面と同じ）.
     */
    private Map<String, String> initRadioLicense() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 既婚、未婚をMapに格納
        radio.put("有", "true");
        radio.put("無", "false");

        return radio;
    }
    
    private Map<String, String> initRadioSex() {
        Map<String, String> sex = new LinkedHashMap<>();
        sex.put("男", "true");
        sex.put("女", "false");
        return sex;
    }

    /**
     * ホーム画面のGET用メソッド
     */
    @GetMapping("/home")
    public String getHome(Model model) {
    	
    	List<UserConsultation> list = userConsultationService.getAll();

		model.addAttribute("consultationsList", list);
		model.addAttribute("title", "home");
		
        //コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "login/home :: home_contents");

        return "login/homeLayout";
    }

    /**
     * ユーザー一覧画面のGETメソッド用処理.
     */
    @GetMapping("/myPage")
    public String getMyPage(Model model) {

        //コンテンツ部分にユーザー一覧を表示するための文字列を登録
        model.addAttribute("contents", "login/myPage :: myPage_contents");

        //ユーザー一覧の生成
        List<User> myPage = userService.selectMany();
        
        //Modelにユーザーリストを登録
        model.addAttribute("myPage", myPage);

//        //データ件数を取得
//        int count = userService.count();
//        model.addAttribute("myPageCount", count);

        return "login/homeLayout";
    }

    /**
     * ユーザー詳細画面のGETメソッド用処理.
     */
    @GetMapping("/userDetail/{name}")
    public String getUserDetail(@ModelAttribute SignupForm form,
            Model model,
            @PathVariable("name") String userName) {

        // ユーザーID確認（デバッグ）
        System.out.println("userName = " + userName);

        // コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "login/userDetail :: userDetail_contents");

        // 結婚ステータス用ラジオボタンの初期化
        radioLicense = initRadioLicense();
        radioSex = initRadioSex();

        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioLicense", radioLicense);
        model.addAttribute("radioSex", radioSex);

        // ユーザーIDのチェック
        if (userName != null && userName.length() > 0) {

            // ユーザー情報を取得
            User user = userService.selectOne(userName);

            // Userクラスをフォームクラスに変換
            form.setUserName(user.getUserName()); //ユーザー名
            form.setSex(user.isSex()); //性別
            form.setAge(user.getAge()); //年齢
            form.setMailAddress(user.getMailAddress()); //メールアドレス
            form.setPassword(user.getPassword()); //パスワード
            form.setLicense(user.isLicense()); //資格有無

            // Modelに登録
            model.addAttribute("signupForm", form);
        }

        return "login/homeLayout";
    }
    

    /**
     * ユーザー更新用処理.
     */
    @PostMapping(value = "/userDetail/{name}", params = "update")
    public String postUserDetailUpdate(@PathVariable("name") SignupForm form,
            Model model) {

        System.out.println("更新ボタンの処理");

        //Userインスタンスの生成
        User user = new User();

        //フォームクラスをUserクラスに変換
        user.setUserName(form.getUserName()); //ユーザー名
        user.setSex(form.isSex()); //性別
        user.setAge(form.getAge()); //年齢
        user.setMailAddress(form.getMailAddress()); //メールアドレス
        user.setPassword(form.getPassword()); //パスワード
        user.setLicense(form.isLicense()); //資格有無

        try {
            //更新実行
            boolean result = userService.updateOne(user);

            model.addAttribute("result", "更新成功");
           
        } catch(DataAccessException e) {

            model.addAttribute("result", "更新失敗(トランザクションテスト)");
        }

        //ユーザー一覧画面を表示
        return getMyPage(model);
    }

    /**
     * ユーザー削除用処理.
     */
    //URLは同じだけどpramsが別の時に処理を分けることができる
    @PostMapping(value = "/userDetail" ,params = "delete")
    public String postUserDetailDelete(@ModelAttribute SignupForm form,
            Model model) {

        System.out.println("削除ボタンの処理");

        //削除実行
        boolean result = userService.deleteOne(form.getUserName());

        if (result == true) {

            model.addAttribute("result", "削除成功");

        } else {

            model.addAttribute("result", "削除失敗");

        } 

        //ユーザー一覧画面を表示
        return getMyPage(model);
    }

    /**
     * ログアウト用処理.
     */
    @PostMapping("/logout")
    public String postLogout() {

        //ログイン画面にリダイレクト
        return "login";
    }

 
    /**
     * アドミン権限専用画面のGET用メソッド.
     * @param model Modelクラス
     * @return 画面のテンプレート名
     */
    @GetMapping("/admin")
    public String getAdmin(Model model) {

        //コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "login/admin :: admin_contents");

        //レイアウト用テンプレート
        return "login/homeLayout";
    }
}
