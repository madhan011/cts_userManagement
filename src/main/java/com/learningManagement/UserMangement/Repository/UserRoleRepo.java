package com.learningManagement.UserMangement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningManagement.UserMangement.model.UserRole;
import com.learningManagement.UserMangement.model.Users;

@Repository
public interface UserRoleRepo  extends JpaRepository<UserRole,Long>{

	UserRole findByUser(Users user);

}
