package com.badenia.feedback.feedbacksystem.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Question_Option")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionTableModel {

	@Id
	private Long id;
	
	@Column(name = "question_type_id")
	private Long questionTypeId;
	
}
