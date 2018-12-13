package com.badenia.feedback.feedbacksystem.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.badenia.feedback.feedbacksystem.exceptions.EntityNotFoundException;
import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Answer;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@RequestMapping("/v1/feedback/answers")
@Getter(value = AccessLevel.PROTECTED)
public class AnswerController {

	@Autowired
	private IFeedbackService feedbackService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> saveAnswer(@Valid @RequestBody Answer model) throws EntityNotFoundException {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(getFeedbackService().saveAnswer(model)).toUri();
		return ResponseEntity.created(uri).build();
	}

}
