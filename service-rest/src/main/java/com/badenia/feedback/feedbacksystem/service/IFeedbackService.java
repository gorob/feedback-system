package com.badenia.feedback.feedbacksystem.service;

import java.util.List;
import java.util.Optional;

import com.badenia.feedback.feedbacksystem.service.model.Event;

public interface IFeedbackService {

	List<Event> findEvents();
	
	Optional<Event> findEventById(Long id);
	
	
}
