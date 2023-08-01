package com.example.demo.Form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignUpForm {
	
	/**
	 * ログインID
	 */
	@Length(min=8,max=20)
	private String loginId;
	
	/**
	 * パスワード
	 */
	@Length(min=8,max=20)
	private String password;

}
