package com.example.demo.Service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Form.SignUpForm;
import com.example.demo.Repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpServise {
	/**
	 * ユーザー情報テーブル DAO
	 */
	private final UserInfoRepository repository;
	
	private final Mapper mapper;
	
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー情報テーブル 新規登録
	 * 
	 * @param form 入力情報
	 * @return 登録情報（ユーザー情報Entity）、既に同じユーザーIDで登録がある場合はEmpty
	 */
	public Optional<UserInfo> resistUserInfo(SignUpForm form){
		
		var userInfoExistedOpt = repository.findById(form.getLoginId());
		if(userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}
		
		var userInfo = mapper.map(form,UserInfo.class);
		
		var encodedPassword= passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);
		
		return Optional.of(repository.save(userInfo));
		
		 
		
	}
	
}


