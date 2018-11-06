package com.badenia.feedback.feedbacksystem.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name = "Question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTableModel {

	@Id
	private Long id;
	
	@Column(name = "eventId")
	private Long eventId;
	
	@Column
	@NonNull
	private String question;
	
}
