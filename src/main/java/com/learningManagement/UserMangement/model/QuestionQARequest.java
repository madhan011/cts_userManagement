package com.learningManagement.UserMangement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionQARequest {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("answer")
	private String answer;

}
