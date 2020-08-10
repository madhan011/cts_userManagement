package com.learningManagement.UserMangement.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningManagement.UserMangement.Repository.UserCourseDetailRepo;
import com.learningManagement.UserMangement.config.RestTemplateConfig;
import com.learningManagement.UserMangement.model.CourseResponse;
import com.learningManagement.UserMangement.model.QuestionQARequest;
import com.learningManagement.UserMangement.model.RestResponse;
import com.learningManagement.UserMangement.model.UserCourseDetail;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class UserCourseDetailServiceImpl implements UserCourseDetailService{

	@Autowired
	UserCourseDetailRepo userCourseDetailRepo;
	
	@Autowired
	RestTemplate myRestTemplate;
	
	@Autowired
	RestTemplateConfig restTemplateConfig;
	
	@Autowired
	ObjectMapper mapper;
	
	SimpleDateFormat sdf	=	 new SimpleDateFormat("dd/MM/yyyy");
	
	@HystrixCommand(fallbackMethod = "callRegisterUser_Fallback")
	public String registerUser(String courseDetails, String username) throws Exception {
		String returnString	=	"REGISTRATION FAILED";
		try {
			
			
			
			RestResponse restResponse	=	callCourseService(courseDetails,"");
			
			if(restResponse!=null && restResponse.getStatuscode().equalsIgnoreCase("200") && restResponse.getData()!=null) {
				CourseResponse	courseResponse	=	 mapper.readValue(mapper.writeValueAsString(restResponse.getData()),CourseResponse.class);
				List<UserCourseDetail> courselist	=	userCourseDetailRepo.findByUsernameAndCourseId(username, courseResponse.getId());
				if(courselist==null || courselist.isEmpty())
				{
					UserCourseDetail ucd	=	 new UserCourseDetail();
					
					ucd.setCourseId(Long.parseLong(courseDetails));
					ucd.setCourseTitle(courseResponse.getCourseTitle());
					ucd.setCourseStatus("REGISTERED");
					ucd.setUsername(username);
					ucd.setCourseStartDate(getStartandExpiryDate(0));
					ucd.setCourseExpiryDate(getStartandExpiryDate(30));
					ucd.setMockStatus(null);
					ucd.setMockResult(null);
					ucd.setMockStartDate(null);
					ucd.setMockEndDate(null);
					ucd.setCourseCompleteDate(null);
				
				
				userCourseDetailRepo.saveAndFlush(ucd);
				returnString	=	"Course Registered Successfully.";
				}else {
					returnString	=	"Course Already Registered.";
				}
			
			}else if(restResponse!=null) {
				returnString	=	restResponse.getStatusMessage();
			}
		}catch(Exception e) {
			System.out.println(e);
			throw e;
		}
		return returnString;
	}
	
	
	private Date getStartandExpiryDate(Integer addDays) {
		
		Calendar c	=	Calendar.getInstance();
		c.add(Calendar.DATE, addDays);
		
		return c.getTime();
	}

	@HystrixCommand(fallbackMethod = "callvalidateMockTest_Fallback")
	public UserCourseDetail validateMockTest(String questionAnswer, String course, String username) throws Exception {
		Integer mark	=0;
		UserCourseDetail ucd	=	 null;
		ObjectMapper mapper	=	 new ObjectMapper();
		try {
			
		List<QuestionQARequest> qaMockList	=	Arrays.asList(mapper.readValue(questionAnswer, QuestionQARequest[].class));
		if(qaMockList!=null && qaMockList.size()>0) {
						
			HttpEntity<?> httpEntity	=	new  HttpEntity(qaMockList,restTemplateConfig.getHttpHeaders());
			
			ResponseEntity<RestResponse> responseEntity	=	myRestTemplate.exchange(restTemplateConfig.getApi()+"/validate",HttpMethod.POST,httpEntity,RestResponse.class);
			
			if(responseEntity!=null && responseEntity.hasBody()) {
				
				RestResponse restResponse	=	responseEntity.getBody();
				if(restResponse!=null && restResponse.getStatuscode().equalsIgnoreCase("200")) {
					mark	=	(Integer)restResponse.getData();
					
					List<UserCourseDetail> courselist	=	userCourseDetailRepo.findByUsernameAndCourseId(username, Long.parseLong(course));
					if(courselist!=null && !courselist.isEmpty()) {
						 ucd	=	courselist.get(0);
						ucd.setMockEndDate(new Date());
						if(mark>5) {
						ucd.setMockStatus("COMPLETED");
						ucd.setCourseStatus("COMPLETED");
						ucd.setCourseCompleteDate(new Date());
						}else {
							ucd.setMockStatus("FAILED");
							ucd.setCourseStatus("PROCESSED");
						}
						ucd.setMockEndDate(new Date());
						ucd.setMockResult(mark);
						
						userCourseDetailRepo.save(ucd);
						
					}
					
				}
			}
		}
		}catch(Exception e) {
			System.out.println("Exception in MockData"+e.toString());
			throw e;
		}
		return ucd;
	}
	
	private RestResponse callCourseService(String courseId,String EndPoint) throws JsonMappingException, JsonProcessingException {
		RestResponse restResponse	=	 null;
		
		UriComponentsBuilder uri	=	 UriComponentsBuilder.fromHttpUrl("http://cousermanagement/course"+"/update").queryParam("Id", courseId);
		
		
		HttpEntity<?> httpEntity	=	new  HttpEntity(null,restTemplateConfig.getHttpHeaders());
		
		ResponseEntity<RestResponse> responseEntity	=	myRestTemplate.exchange(uri.build(false).toString(),
				HttpMethod.POST,httpEntity,RestResponse.class);
		
		
		return responseEntity.getBody();
		
	}


	@Override
	public List<UserCourseDetail> getAllCoursesByUsername(String username) {

		return userCourseDetailRepo.findByUsername(username);
	}
	
	private UserCourseDetail callvalidateMockTest_Fallback(String questionAnswer, String course,String username) {
		System.out.println("callvalidateMockTest_Fallback");
		UserCourseDetail ucd	=	new UserCourseDetail();
		ucd.setUsername("Registration Failed");
		return ucd;
	}
	
	private String callRegisterUser_Fallback(String courseDetails, String username) {
		System.out.println("callRegisterUser_Fallback");
		return "Unable to Proceed. Please contact Adminsitsrator";
	}

}
