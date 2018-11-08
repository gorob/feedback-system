package com.badenia.feedback.feedbacksystem.repository.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerTableModel {

	@Id
	private Long id;
	
	@Column(name = "answered_at")
	private Date answeredAt;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "question_option_id")
	private Long questionOptionId;
	
	@Column(name = "question_id")
	private Long question_id;
	
	
	
}
