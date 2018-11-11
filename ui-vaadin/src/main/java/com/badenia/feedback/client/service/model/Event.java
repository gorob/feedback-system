package com.badenia.feedback.client.service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

	private Long id;
	
	private String name;
	
	private List<Question> questions;
	
}
