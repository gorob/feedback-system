package com.badenia.feedback.feedbacksystem.service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {

	private Long id;
	
	private String name;
	
	private List<Question> questions;
	
}
