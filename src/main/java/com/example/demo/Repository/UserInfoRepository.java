package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.example.demo.Entity.UserInfo;




/**
 * @Repository
 * JpaRepositoryを継承している場合、
 * Springは自動的にそのインターフェースをリポジトリとして認識し、
 * @Repositoryアノテーションを追加する必要はありません。
 * リポジトリクラスの役割を果たすことが保証されています。
 * */

public interface UserInfoRepository extends JpaRepository<UserInfo,String>{
	
	

}
