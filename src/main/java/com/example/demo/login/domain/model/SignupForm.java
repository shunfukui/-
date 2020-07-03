package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	
	//必須入力
    @NotBlank(groups = ValidGroup1.class, message = "{require_check}")
    private String userName; // 名前
    
    //falseのみ可能
    private boolean sex; // 性別
    
    //値が20から100まで
    
    @Min(value = 0, groups = ValidGroup2.class, message = "{min_check}")
    @Max(value = 100, groups = ValidGroup2.class, message = "{max_check}")
    private int age; // 年齢

    //必須入力、メールアドレス形式
    @NotEmpty
    @NotBlank(groups = ValidGroup1.class, message = "{require_check}")
    @Email(groups = ValidGroup2.class, message = "{email_check}")
    private String mailAddress; // メールアドレス

    //必須入力、長さ4から100桁まで、半角英数字のみ
    @NotBlank(groups = ValidGroup1.class, message = "{require_check}")
    @Length(groups = ValidGroup2.class, message = "{length_check}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class, message = "{pattern_check}")
    private String password; // パスワード
    
    
    
    private boolean license; // 資格有無
    
}
