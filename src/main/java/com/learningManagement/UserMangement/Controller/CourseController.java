package com.learningManagement.UserMangement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learningManagement.UserMangement.model.QuestionQARequest;
import com.learningManagement.UserMangement.model.RestResponse;
import com.learningManagement.UserMangement.service.UserCourseDetailService;

@RestController
@RequestMapping("/user")
public class CourseController {
	
	@Autowired
	UserCourseDetailService userCourseDetailService;
	
	@PostMapping("/courseRegister")
	public ResponseEntity<RestResponse> registerCourse(@RequestParam String courseId,@RequestParam String username)
	{
		
		try {
			if(courseId!=null) {
				return ResponseEntity.ok().body(new RestResponse("200",userCourseDetailService.registerUser(courseId,username),"Sucess"));
			}else {
				return ResponseEntity.ok().body(new RestResponse("400","Invalid Input","Failure"));
			}
		}catch(Exception e) {
			return  ResponseEntity.ok().body(new RestResponse("500","Registration Failure.Please Try Again","Failure"));
		}
	}
	
	@PostMapping("/validateMock")
	public ResponseEntity<RestResponse> validate(@RequestBody String questionAnswer,@RequestParam String courseId,@RequestParam String username)
	{
		try {
			if(courseId!=null) {
				return ResponseEntity.ok().body(new RestResponse("200",userCourseDetailService.validateMockTest(questionAnswer, courseId, username),"Sucess"));
			}else {
				return ResponseEntity.ok().body(new RestResponse("400","Invalid Input","Failure"));
			}
		}catch(Exception e) {
			System.out.print("TEst"+e.toString());
			return  ResponseEntity.ok().body(new RestResponse("500","Registration Failure.Please Try Again","Failure"));
		}
	}
	
	@PostMapping("/userCourse")
	public ResponseEntity<RestResponse> validate(@RequestParam String username)
	{
		try {
			if(username!=null) {
				return ResponseEntity.ok().body(new RestResponse("200",userCourseDetailService.getAllCoursesByUsername(username),"Sucess"));
			}else {
				return ResponseEntity.ok().body(new RestResponse("400","Invalid Input","Failure"));
			}
		}catch(Exception e) {
			return  ResponseEntity.ok().body(new RestResponse("500","Registration Failure.Please Try Again","Failure"));
		}
	}

}
