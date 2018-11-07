package com.badenia.feedback.feedbacksystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badenia.feedback.feedbacksystem.repository.model.QuestionTableModel;

public interface QuestionRepository extends JpaRepository<QuestionTableModel, Long> {

	List<QuestionTableModel> findAllByEventId(Long eventId);
	
	Optional<QuestionTableModel> findByEventIdAndId(Long eventId, Long id);
	
}
