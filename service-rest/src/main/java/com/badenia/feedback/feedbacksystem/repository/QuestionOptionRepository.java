package com.badenia.feedback.feedbacksystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badenia.feedback.feedbacksystem.repository.model.QuestionOptionTableModel;

public interface QuestionOptionRepository extends JpaRepository<QuestionOptionTableModel, Long> {

	
	List<QuestionOptionTableModel> findAllByQuestionTypeId(Long questionTypeId);
	
	
	
	
}
