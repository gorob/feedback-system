package com.badenia.feedback.feedbacksystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;
import com.badenia.feedback.feedbacksystem.service.model.Question;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@RequestMapping("/v1/feedback/events/{eventId}/questions/{questionId}/options")
@Getter(value = AccessLevel.PROTECTED)
public class QuestionOptionController {

	@Autowired
	private IFeedbackService feedbackService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Question>> readAll(@PathVariable("eventId") Long eventId) {
		Optional<Event> event = getFeedbackService().findEventById(eventId);
		if (event.isPresent()) {
			if (event.get().getQuestions().isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(event.get().getQuestions());
			}
		} else {
			return ResponseEntity.notFound().build();

		}
	}

}
