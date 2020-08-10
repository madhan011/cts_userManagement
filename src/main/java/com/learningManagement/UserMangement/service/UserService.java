package com.learningManagement.UserMangement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.learningManagement.UserMangement.model.Users;

@Service
public interface UserService extends UserDetailsService{

	Users getAuthenticateUser(String loginModel)  throws Exception;
	
	List<Users> getAllUsers();
	
	Users getUserByUserName(String userName);
	
	Users  save(String user)  throws Exception;
	
	boolean valiadateUserNme(String username) throws Exception;
}
