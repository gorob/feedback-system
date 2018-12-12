package com.badenia.feedback.feedbacksystem.service;

import java.util.List;

import com.badenia.feedback.feedbacksystem.exceptions.EntityNotFoundException;
import com.badenia.feedback.feedbacksystem.service.model.Answer;
import com.badenia.feedback.feedbacksystem.service.model.Event;
import com.badenia.feedback.feedbacksystem.service.model.Question;

public interface IFeedbackService {

	List<Event> findEvents();
	
	Event findEventById(Long id) throws EntityNotFoundException;
	
	Question findQuestion(Long eventId, Long questionId) throws EntityNotFoundException;

	Long saveAnswer(Answer answer) throws EntityNotFoundException;
	
}
