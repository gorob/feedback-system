package com.badenia.feedback.feedbacksystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.badenia.feedback.feedbacksystem.controller.model.QuestionTM;
import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;
import com.badenia.feedback.feedbacksystem.service.repository.model.QuestionTableModel;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@RequestMapping("/v1/feedback/events/{eventId}/questions")
@Getter(value = AccessLevel.PROTECTED)
public class QuestionController {

	@Autowired
	private IFeedbackService feedbackService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<QuestionTM>> readAll(@PathVariable("eventId") Long eventId) {
		Optional<Event> event = getFeedbackService().findEventById(eventId);
		if (event.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			if (event.get().getQuestions().isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(event.get().getQuestions().stream().map(q -> new QuestionTM(q.getId(), q.getName())).collect(Collectors.toList()));
			}
			
		}
	}
	
	protected QuestionTM map(QuestionTableModel model) {
		return new QuestionTM(model.getId(), model.getQuestion());
	}
	
}
