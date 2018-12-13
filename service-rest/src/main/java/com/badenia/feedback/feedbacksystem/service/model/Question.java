package com.badenia.feedback.feedbacksystem.service.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	private List<Option> options;
	
}
