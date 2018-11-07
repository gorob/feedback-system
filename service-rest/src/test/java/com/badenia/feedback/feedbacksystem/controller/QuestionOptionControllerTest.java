package com.badenia.feedback.feedbacksystem.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.badenia.feedback.feedbacksystem.service.repository.QuestionOptionRepository;
import com.badenia.feedback.feedbacksystem.service.repository.QuestionRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = QuestionOptionController.class)
public class QuestionOptionControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	QuestionRepository questionRepositoryMock;
	
	@MockBean
	QuestionOptionRepository questionOptionRepositoryMock;

	@Test
	public void testReadAll_EventOrQuestionNotFound_ReturnHttpStatusNotFound() throws Exception {
		when(questionRepositoryMock.findByEventIdAndId(20L, 1L)).thenReturn(Optional.empty());
	
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/feedback/events/20/questions/1/options").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isNotFound());
		
		verify(questionRepositoryMock).findByEventIdAndId(20L, 1L);
	}
	

}
