package com.learningManagement.UserMangement.model;

import java.io.File;

public class CourseResponse {
	
private Long id;
	
	private String category;
	
	private String courseName;
	
	private String courseTitle;
	
	private File image;
	
	private String description;
	
	private boolean isActive;
	
	private Long subscriptionCount;
	
	private Long maxCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getSubscriptionCount() {
		return subscriptionCount;
	}

	public void setSubscriptionCount(Long subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}

	public Long getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Long maxCount) {
		this.maxCount = maxCount;
	}
	
	

}
