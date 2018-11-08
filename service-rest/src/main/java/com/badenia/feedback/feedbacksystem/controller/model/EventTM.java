package com.badenia.feedback.feedbacksystem.controller.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class EventTM {

	private Long id;
	
	@NonNull
	private String title;

	private String description;

	@NonNull
	private Date startEventDate;

	@NonNull
	private Date endEventDate;

}
