package com.badenia.feedback.feedbacksystem.repository.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventTableModel {

	@Id
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@Column(name = "start_date")
	private Date start;

	@Column(name = "end_date")
	private Date end;

}
