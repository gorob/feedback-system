package com.badenia.feedback.feedbacksystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;
import com.badenia.feedback.feedbacksystem.service.model.Question;
import com.badenia.feedback.feedbacksystem.service.repository.EventRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionRepository;
import com.badenia.feedback.feedbacksystem.service.repository.model.EventTableModel;
import com.badenia.feedback.feedbacksystem.service.repository.model.QuestionTableModel;

import lombok.AccessLevel;
import lombok.Getter;

@Service
@Getter(value = AccessLevel.PROTECTED)
class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public List<Event> findEvents() {
		return getEventRepository().findAll().stream().map(e -> loadEvent(e)).collect(Collectors.toList());
	}

	protected Event loadEvent(EventTableModel event) {
		List<QuestionTableModel> questions = getQuestionRepository().findAllByEventId(event.getId());
		return new Event(event.getId(), event.getName(),
				questions.stream().map(q -> new Question(q.getId(), q.getQuestion())).collect(Collectors.toList()));
	}

	@Override
	public Optional<Event> findEventById(Long id) {
		Optional<EventTableModel> event = getEventRepository().findById(id);
		return event.isEmpty() ? Optional.empty() : Optional.of(loadEvent(event.get()));
	}

}
