package com.badenia.feedback.feedbacksystem.service.impl;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;
import com.badenia.feedback.feedbacksystem.service.repository.AnswerRepository;
import com.badenia.feedback.feedbacksystem.service.repository.EventRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionOptionRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionTypeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FeedbackServiceImplDBTest {

	@TestConfiguration
	public static class TestConf {
		
	}
	
	@Autowired
	public AnswerRepository answerRepository;
	
	@Autowired
	public EventRepository eventRepository;
	
	@Autowired
	public QuestionOptionRepository questionOptionRepository;
	
	@Autowired
	public QuestionRepository questionRepository;
	
	@Autowired
	public QuestionTypeRepository questionTypeRepository;
	
	

	@Test
	public void testFindEventById() throws Exception {
		
		IFeedbackService service = new FeedbackServiceImpl()  {
			
			@Override
			protected EventRepository getEventRepository() {
				return eventRepository;
			}
			
			@Override
			protected QuestionRepository getQuestionRepository() {
				return questionRepository;
			}
			
		};
		
		Optional<Event> event =  service.findEventById(1L);
	}
	
}
