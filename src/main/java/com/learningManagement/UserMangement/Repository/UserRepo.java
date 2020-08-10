package com.learningManagement.UserMangement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningManagement.UserMangement.model.UserRole;
import com.learningManagement.UserMangement.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
	
	@SuppressWarnings("unchecked")
	Users save(Users userDetails);
	
	Users findByUserName(String userName);
	
	Users findByUserNameAndPasswordAndIsValid(String userName, String password,boolean isValid);
	
	List<Users> findAll();


}
