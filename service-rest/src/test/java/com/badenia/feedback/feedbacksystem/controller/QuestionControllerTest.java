package com.badenia.feedback.feedbacksystem.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.badenia.feedback.feedbacksystem.repository.EventRepository;
import com.badenia.feedback.feedbacksystem.repository.QuestionRepository;
import com.badenia.feedback.feedbacksystem.repository.model.EventTableModel;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

	@MockBean
	QuestionRepository questionRepositoryMock;
	
	@MockBean
	EventRepository eventRepositoryMock;
	
	@Autowired
	MockMvc mockMvc;

	@Test
	public void testReadAll_NoEventFoundForId20_ReturnsHttpStatusNotFound() throws Exception {
		when(eventRepositoryMock.findById(20L)).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/feedback/events/20/questions")).andExpect(MockMvcResultMatchers.status().isNotFound());
		
		verify(eventRepositoryMock).findById(20L);
		
	}

	@Test
	public void testReadAll_NoQuestionsFound_ReturnHttpStatusNoContent() throws Exception {
		when(eventRepositoryMock.findById(20L)).thenReturn(Optional.of(new EventTableModel()));
		when(questionRepositoryMock.findAllByEventId(20L)).thenReturn(new ArrayList<>());
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/feedback/events/20/questions")).andExpect(MockMvcResultMatchers.status().isNoContent());
		
		verify(eventRepositoryMock).findById(20L);
		verify(questionRepositoryMock).findAllByEventId(20L);
	}
	
}
