package com.learningManagement.UserMangement.model;

import org.springframework.stereotype.Component;

@Component
public class RestResponse {

	private String statuscode;
	private Object data;
	private String statusMessage;
	public String getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Override
	public String toString() {
		return "RestResponse [statuscode=" + statuscode + ", data=" + data + ", statusMessage=" + statusMessage + "]";
	}
	public RestResponse(String statuscode, Object data, String statusMessage) {
		super();
		this.statuscode = statuscode;
		this.data = data;
		this.statusMessage = statusMessage;
	}
	public RestResponse() {
		super();
	}
}
