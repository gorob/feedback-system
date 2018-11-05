package com.badenia.feedback.feedbacksystem.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EventTableModel {

	@Id
	private Long id;

	private String name;

	public EventTableModel() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
