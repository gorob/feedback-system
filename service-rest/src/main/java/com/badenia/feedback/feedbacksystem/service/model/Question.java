package com.badenia.feedback.feedbacksystem.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Question {

	private Long id;
	
	private String questionName;
	
	@JsonFormat(shape =Shape.STRING)
	private QuestionType questionType;
	
}
