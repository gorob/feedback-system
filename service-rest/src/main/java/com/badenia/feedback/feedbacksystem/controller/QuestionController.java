package com.badenia.feedback.feedbacksystem.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.badenia.feedback.feedbacksystem.exceptions.EntityNotFoundException;
import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;
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
	public ResponseEntity<List<Question>> readQuestions(@PathVariable("eventId") Long eventId)
			throws EntityNotFoundException {
		Event event = getFeedbackService().findEventById(eventId);
		if (event.getQuestions().isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(event.getQuestions());
		}
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/{questionId}")
	public ResponseEntity<Question> readQuestion(@PathVariable("eventId") Long eventId,
			@PathVariable("questionId") Long questionId) throws EntityNotFoundException {
		Question question = getFeedbackService().findQuestion(eventId, questionId);
		return ResponseEntity.ok(question);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuestion(@PathVariable("eventId") Long eventId,
			@Valid @RequestBody Question question) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(getFeedbackService().save(eventId, question)).toUri();
		return ResponseEntity.created(uri).build();

	}

}
