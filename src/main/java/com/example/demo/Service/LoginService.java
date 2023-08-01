package com.example.demo.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final UserInfoRepository repository;
			
	public Optional<UserInfo> searchUserById(String loginId){
		
		return repository.findById(loginId);
		
	}
	
}


