package com.learningManagement.UserMangement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learningManagement.UserMangement.config.JwtTokenUtil;
import com.learningManagement.UserMangement.model.JwtResponse;
import com.learningManagement.UserMangement.model.LoginRequest;
import com.learningManagement.UserMangement.model.RestResponse;
import com.learningManagement.UserMangement.model.Users;
import com.learningManagement.UserMangement.service.UserService;

@RestController
public class UserController{
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@RequestMapping(value ="/loginUser", method = RequestMethod.POST)
	public ResponseEntity<RestResponse> getUser(@RequestBody LoginRequest loginDetails)
	{
		try {
			authenticate(loginDetails.getUserName(), loginDetails.getPassword());

			final UserDetails userDetails = userService
					.loadUserByUsername(loginDetails.getUserName());

			final String token = jwtTokenUtil.generateToken(userDetails);
			
			

			return ResponseEntity.ok(new RestResponse("200",new JwtResponse(token),"SUCCESS"));
		}catch(Exception e) {
			return ResponseEntity.ok().body(new RestResponse("505",null,"Internal Server Error"));
		}
		
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public ResponseEntity<RestResponse> registerUser(@RequestBody String userDetails)
	{
		try {
			Users	user	=	userService.save(userDetails);
			if(user!=null) {
				return ResponseEntity.ok().body(new RestResponse("200",(Object) user,"Success"));
			}else {
				return ResponseEntity.ok().body(new RestResponse("404",null,"Invalid Credientals"));
			}
		}catch(Exception e) {
			return ResponseEntity.ok().body(new RestResponse("505",null,"Internal Server Error"));
		}
	}
	
	@GetMapping("/validate/{username}")
	public ResponseEntity<?> checkUserName(@PathVariable String username){
		
		try {
			
		 return ResponseEntity.ok().body(new RestResponse("200",userService.valiadateUserNme(username),"Success"));
			
		}catch(Exception e) {
			 return ResponseEntity.ok().body(new RestResponse("500",null,"Failure"));
		}
	}
	
	@RequestMapping(value ="/test", method = RequestMethod.GET)
	public ResponseEntity<RestResponse> getTest()
	{
		System.out.println("Test");
		
		return ResponseEntity.ok(null);
	}
	
}


