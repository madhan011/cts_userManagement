package com.learningManagement.UserMangement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningManagement.UserMangement.Repository.UserRepo;
import com.learningManagement.UserMangement.Repository.UserRoleRepo;
import com.learningManagement.UserMangement.model.LoginRequest;
import com.learningManagement.UserMangement.model.UserRole;
import com.learningManagement.UserMangement.model.Users;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	UserRoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public Users getAuthenticateUser(String loginModel) {
		Users	user=null;
		try {
			LoginRequest loginrequest	=	mapper.readValue(loginModel, LoginRequest.class);
			user	=	userRepo.findByUserNameAndPasswordAndIsValid(loginrequest.getUserName(), loginrequest.getPassword(), Boolean.TRUE);
			
		}catch(Exception e) {
			System.out.println("Exception in getAuthenticate Login");
		}
		return user;
	}

	@Override
	public List<Users> getAllUsers() {
		// 
		return userRepo.findAll();
	}

	

	@Override
	public Users getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepo.findByUserName(userName);
	}

	@Override
	public Users save(String userDetails) throws Exception {
		Users returnuser	=	null;
		try {
			 Users user	=	mapper.readValue(userDetails, Users.class);
			 user.setPassword(bcryptEncoder.encode(user.getPassword()));
			 user.setRole(new UserRole(user,"ROLE_USER"));
			 user.setValid(true);
			 returnuser	=	userRepo.save(user);
			
		}catch(Exception e) {
			throw e;
		}
		return returnuser;
	}

	@Override
	public boolean valiadateUserNme(String username) throws Exception {
		boolean bflag	=	 false;
		if(username!=null) {
			Users user	=	userRepo.findByUserName(username);
			if(user!=null) {
				bflag	=	 true;
			}
		}
		return bflag;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user	=	getUserByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		UserRole userrole	=	 roleRepo.findByUser(user);
		return new org.springframework.security.core.userdetails.User
		(user.getUserName(),user.getPassword(),getAuthorities(Arrays.asList(userrole))); 	

	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(
			  Collection<UserRole> roles) {
			    List<GrantedAuthority> authorities
			      = new ArrayList<>();
			    for (UserRole role: roles) {
			        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			    }
			    return authorities;
			}

}
