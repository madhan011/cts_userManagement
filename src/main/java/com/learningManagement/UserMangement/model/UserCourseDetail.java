package com.learningManagement.UserMangement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="USER_COURSE")
public class UserCourseDetail {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long registerId;
	
	@NotNull
	private String username;
	
	@NotNull
	private Long courseId;
		
	private String courseTitle;
	
	private String courseStatus;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_IN", timezone = "Asia/Kolkata")
	private Date courseStartDate;
	
	private String mockStatus;
	
	private Integer	mockResult;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_IN", timezone = "Asia/Kolkata")
	private Date courseExpiryDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_IN", timezone = "Asia/Kolkata")
	private Date mockStartDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_IN", timezone = "Asia/Kolkata")
	private Date courseCompleteDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_IN", timezone = "Asia/Kolkata")
	private Date mockEndDate;

	public Long getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Long registerId) {
		registerId = registerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Date getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public String getMockStatus() {
		return mockStatus;
	}

	public void setMockStatus(String mockStatus) {
		this.mockStatus = mockStatus;
	}

	public Integer getMockResult() {
		return mockResult;
	}

	public void setMockResult(Integer mockResult) {
		this.mockResult = mockResult;
	}

	public Date getCourseExpiryDate() {
		return courseExpiryDate;
	}

	public void setCourseExpiryDate(Date courseExpiryDate) {
		this.courseExpiryDate = courseExpiryDate;
	}

	public Date getMockStartDate() {
		return mockStartDate;
	}

	public void setMockStartDate(Date mockStartDate) {
		this.mockStartDate = mockStartDate;
	}

	public Date getCourseCompleteDate() {
		return courseCompleteDate;
	}

	public void setCourseCompleteDate(Date courseCompleteDate) {
		this.courseCompleteDate = courseCompleteDate;
	}

	public Date getMockEndDate() {
		return mockEndDate;
	}

	public void setMockEndDate(Date mockEndDate) {
		this.mockEndDate = mockEndDate;
	}

	@Override
	public String toString() {
		return "UserCourseDetail [registerId=" + registerId + ", username=" + username + ", courseId=" + courseId
				+ ", courseTitle=" + courseTitle + ", courseStatus=" + courseStatus + ", courseStartDate="
				+ courseStartDate + ", mockStatus=" + mockStatus + ", mockResult=" + mockResult + ", courseExpiryDate="
				+ courseExpiryDate + ", mockStartDate=" + mockStartDate + ", courseCompleteDate=" + courseCompleteDate
				+ ", mockEndDate=" + mockEndDate + "]";
	}

	public UserCourseDetail(Long registerId, String username, Long courseId, String courseTitle, String courseStatus,
			Date courseStartDate, String mockStatus, Integer mockResult, Date courseExpiryDate, Date mockStartDate,
			Date courseCompleteDate, Date mockEndDate) {
		super();
		this.registerId = registerId;
		this.username = username;
		this.courseId = courseId;
		this.courseTitle = courseTitle;
		this.courseStatus = courseStatus;
		this.courseStartDate = courseStartDate;
		this.mockStatus = mockStatus;
		this.mockResult = mockResult;
		this.courseExpiryDate = courseExpiryDate;
		this.mockStartDate = mockStartDate;
		this.courseCompleteDate = courseCompleteDate;
		this.mockEndDate = mockEndDate;
	}

	public UserCourseDetail() {
		super();
	}

	
}
