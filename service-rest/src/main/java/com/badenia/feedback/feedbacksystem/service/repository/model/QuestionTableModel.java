package com.badenia.feedback.feedbacksystem.service.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name = "Question")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(sequenceName = "SEQ_QUESTION", name = "SEQ_QUESTION_GEN")
public class QuestionTableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QUESTION_GEN")
	private Long id;

	@Column(name = "event_id")
	private Long eventId;

	@Column(name = "question_type_id")
	private Long questionTypeId;

	@Column(name = "question")
	@NonNull
	private String questionTitle;

}
