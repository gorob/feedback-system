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

import com.badenia.feedback.feedbacksystem.controller.model.QuestionOptionTM;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionOptionRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionRepository;
import com.badenia.feedback.feedbacksystem.service.repository.model.QuestionOptionTableModel;
import com.badenia.feedback.feedbacksystem.service.repository.model.QuestionTableModel;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@RequestMapping("/v1/feedback/events/{eventId}/questions/{questionId}/options")
@Getter(value = AccessLevel.PROTECTED)
public class QuestionOptionController {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionOptionRepository questionOptionRepository;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<QuestionOptionTM>> readAll(@PathVariable("eventId") Long eventId,
			@PathVariable("questionId") Long questionId) {
		Optional<QuestionTableModel> question = getQuestionRepository().findByEventIdAndId(eventId, questionId);
		if (question.isPresent()) {
			List<QuestionOptionTableModel> allOptions = getQuestionOptionRepository().findAllByQuestionTypeId(question.get().getQuestionTypeId());
			if (allOptions.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(allOptions.stream().map(this::map).collect(Collectors.toList()));
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	protected QuestionOptionTM map(QuestionOptionTableModel model) {
		return new QuestionOptionTM(model.getId());
	}

}
