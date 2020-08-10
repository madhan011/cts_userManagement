package com.learningManagement.UserMangement.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningManagement.UserMangement.Repository.UserRepo;
import com.learningManagement.UserMangement.Repository.UserRoleRepo;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepo userRepo;
	
	@Mock
	ObjectMapper mapper;
	
	@Mock
	UserRoleRepo roleRepo;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	
}
