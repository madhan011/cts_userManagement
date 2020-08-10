package com.learningManagement.UserMangement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningManagement.UserMangement.model.UserCourseDetail;

@Repository
public interface UserCourseDetailRepo extends JpaRepository<UserCourseDetail, Long>{
	
	@SuppressWarnings("unchecked")
	UserCourseDetail save(UserCourseDetail userCourseDetail);
	
	List<UserCourseDetail> findByUsername(String userName);
	
	List<UserCourseDetail> findByUsernameAndCourseStatus(String username,String status);
	
	List<UserCourseDetail> findByCourseId(Long courseId);
	
	List<UserCourseDetail> findByUsernameAndCourseId(String userName,Long courseId);

}
