package com.badenia.feedback.feedbacksystem.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Event {

	private Long id;
	
	private String name;
	
	@JsonIgnore
	private List<Question> questions;
	
}
