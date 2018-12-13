package com.badenia.feedback.feedbacksystem.service.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badenia.feedback.feedbacksystem.exceptions.EntityNotFoundException;
import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Answer;
import com.badenia.feedback.feedbacksystem.service.model.Event;
import com.badenia.feedback.feedbacksystem.service.model.Option;
import com.badenia.feedback.feedbacksystem.service.model.Question;
import com.badenia.feedback.feedbacksystem.service.model.QuestionType;
import com.badenia.feedback.feedbacksystem.service.repository.AnswerRepository;
import com.badenia.feedback.feedbacksystem.service.repository.EventRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionOptionRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionTypeRepository;
import com.badenia.feedback.feedbacksystem.service.repository.model.AnswerTableModel;
import com.badenia.feedback.feedbacksystem.service.repository.model.EventTableModel;
import com.badenia.feedback.feedbacksystem.service.repository.model.QuestionOptionTableModel;
import com.badenia.feedback.feedbacksystem.service.repository.model.QuestionTableModel;

import lombok.AccessLevel;
import lombok.Getter;

@Transactional
@Service
@Getter(value = AccessLevel.PROTECTED)
class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuestionTypeRepository questionTypeRepository;

	@Autowired
	QuestionOptionRepository questionOptionRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Override
	public List<Event> findEvents() {
		return getEventRepository().findAll().stream().map(this::loadEvent).collect(Collectors.toList());
	}

	protected Event loadEvent(EventTableModel event) {
		List<QuestionTableModel> questions = getQuestionRepository().findAllByEventId(event.getId());
		return Event.builder().id(event.getId()).name(event.getName())
				.questions(questions.stream().map(this::map).collect(Collectors.toList())).build();
	}

	protected Question map(QuestionTableModel question) {
		List<QuestionOptionTableModel> questionOptions = getQuestionOptionRepository()
				.findAllByQuestionTypeId(question.getQuestionTypeId());
		return Question.builder().id(question.getId()).questionName(question.getQuestionTitle())
				.questionType(QuestionType.getForDBId(question.getQuestionTypeId()))
				.options(questionOptions.stream().map(this::map).collect(Collectors.toList())).build();
	}

	protected Option map(QuestionOptionTableModel option) {
		return Option.builder().id(option.getId()).iconPath(option.getIconPath())
				.optionalDescription(option.getOptionalDescription()).build();
	}

	@Override
	public Event findEventById(Long id) throws EntityNotFoundException {
		Optional<EventTableModel> event = getEventRepository().findById(id);
		if (event.isPresent()) {
			return loadEvent(event.get());
		} else {
			throw new EntityNotFoundException(Event.class, "id", id.toString());
		}
	}

	@Override
	public Question findQuestion(Long eventId, Long questionId) throws EntityNotFoundException {
		Event event = findEventById(eventId);
		Optional<Question> question = event.getQuestions().stream().filter(q -> q.getId() == questionId).findFirst();
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new EntityNotFoundException(Question.class, "id", questionId.toString());
		}
	}

	@Override
	public Long saveAnswer(Answer answer) throws EntityNotFoundException {
		AnswerTableModel savedAnswer = getAnswerRepository().save(AnswerTableModel.builder()
				.questionId(answer.getQuestionId()).questionOptionId(answer.getOptionId()).remark(answer.getRemark())
				.answeredAt(Date.from(answer.getAnsweredAt().atZone(ZoneId.systemDefault()).toInstant())).build());
		return savedAnswer.getId();
	}

	@Override
	public Long saveEvent(@Valid Event event) {
		return getEventRepository().save(EventTableModel.builder().name(event.getName()).build()).getId();
	}

	@Override
	public Long save(@NotNull Long eventId, @Valid Question question) {
		return getQuestionRepository().save(QuestionTableModel.builder().eventId(eventId)
				.questionTitle(question.getQuestionName()).questionTypeId(question.getQuestionType().getDbId()).build())
				.getId();
	}

}
