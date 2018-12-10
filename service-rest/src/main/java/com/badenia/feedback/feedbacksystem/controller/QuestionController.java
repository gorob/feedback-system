package com.badenia.feedback.feedbacksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badenia.feedback.feedbacksystem.exceptions.EntityNotFoundException;
import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;
import com.badenia.feedback.feedbacksystem.service.model.Option;
import com.badenia.feedback.feedbacksystem.service.model.Question;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@RequestMapping("/v1/feedback/events/{eventId}/questions")
@Getter(value = AccessLevel.PROTECTED)
public class QuestionController {

	@Autowired
	private IFeedbackService feedbackService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Question>> readAll(@PathVariable("eventId") Long eventId) throws EntityNotFoundException {
		Event event = getFeedbackService().findEventById(eventId);
		if (event.getQuestions().isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(event.getQuestions());
		}
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/{questionId}")
	public ResponseEntity<Question> readOption(@PathVariable("eventId") Long eventId, @PathVariable("questionId") Long questionId) throws EntityNotFoundException {
		Question question = getFeedbackService().findQuestion(eventId, questionId);
		return ResponseEntity.ok(question);
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/{questionId}/options")
	public ResponseEntity<List<Option>> readOptions(@PathVariable Long eventId, @PathVariable Long questionId) throws EntityNotFoundException {
		Question question = getFeedbackService().findQuestion(eventId, questionId);
		if (question.getOptions().isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(question.getOptions());
		}
	}

}
