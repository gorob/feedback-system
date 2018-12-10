package com.badenia.feedback.feedbacksystem.service.model;

import javax.validation.constraints.NotNull;

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
	
	@NotNull
	@JsonFormat(shape =Shape.STRING)
	private QuestionType questionType;
	
}
