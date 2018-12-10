package com.badenia.feedback.feedbacksystem.service;

import java.util.List;

import com.badenia.feedback.feedbacksystem.exceptions.EntityNotFoundException;
import com.badenia.feedback.feedbacksystem.service.model.Event;

public interface IFeedbackService {

	List<Event> findEvents();
	
	Event findEventById(Long id) throws EntityNotFoundException;
	
	
}
