package com.badenia.feedback.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Question {

	public Question() {
		// TODO Auto-generated constructor stub
	}

	private Long id;
	
	private String questionName;
	
}
