package com.badenia.feedback.feedbacksystem.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Question {

	private Long id;
	
	private String questionName;
	
}
