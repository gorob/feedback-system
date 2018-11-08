package com.badenia.feedback.feedbacksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.badenia.feedback.feedbacksystem.repository.model.EventTableModel;

@Repository
public interface EventRepository extends JpaRepository<EventTableModel, Long> {

		
	
}
