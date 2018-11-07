package com.badenia.feedback.feedbacksystem.service.repository.model;

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
	
	@Column(name = "event_id")
	private Long eventId;
	
	@Column(name = "question_type_id")
	private Long questionTypeId;
	
	@Column
	@NonNull
	private String question;
	
}
