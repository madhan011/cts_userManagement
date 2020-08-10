package com.learningManagement.UserMangement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learningManagement.UserMangement.model.QuestionQARequest;
import com.learningManagement.UserMangement.model.UserCourseDetail;

@Service
public interface UserCourseDetailService {

	String registerUser(String courseDetails, String username)throws Exception;
	
	UserCourseDetail validateMockTest(String questionAnswer,String Couerse,String username) throws Exception;
	
	List<UserCourseDetail> getAllCoursesByUsername(String username);
}
