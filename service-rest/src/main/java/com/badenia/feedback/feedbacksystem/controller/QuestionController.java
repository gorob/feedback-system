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
import com.badenia.feedback.feedbacksystem.repository.EventRepository;
import com.badenia.feedback.feedbacksystem.repository.QuestionRepository;
import com.badenia.feedback.feedbacksystem.repository.model.EventTableModel;
import com.badenia.feedback.feedbacksystem.repository.model.QuestionTableModel;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@RequestMapping("/v1/feedback/events/{eventId}/questions")
@Getter(value = AccessLevel.PROTECTED)
public class QuestionController {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<QuestionTM>> readAll(@PathVariable("eventId") Long eventId) {
		Optional<EventTableModel> event = eventRepository.findById(eventId);
		if (event.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			List<QuestionTableModel> questions = questionRepository.findAllByEventId(eventId);
			if (questions.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(questions.stream().map(this::map).collect(Collectors.toList()));
			}
			
		}
	}
	
	protected QuestionTM map(QuestionTableModel model) {
		return new QuestionTM(model.getId(), model.getQuestion());
	}
	
}
