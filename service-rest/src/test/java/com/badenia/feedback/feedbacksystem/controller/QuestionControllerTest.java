package com.badenia.feedback.feedbacksystem.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
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

import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

	@MockBean
	IFeedbackService feedbackServiceMock;
	
	@Autowired
	MockMvc mockMvc;

	@Test
	public void testReadAll_NoEventFoundForId20_ReturnsHttpStatusNotFound() throws Exception {
		when(feedbackServiceMock.findEventById(20L)).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/feedback/events/20/questions")).andExpect(MockMvcResultMatchers.status().isNotFound());
		
		verify(feedbackServiceMock).findEventById(20L);
		
	}

	@Test
	public void testReadAll_NoQuestionsFound_ReturnHttpStatusNoContent() throws Exception {
		when(feedbackServiceMock.findEventById(20L)).thenReturn(Optional.of(new Event(20L, "Question", Collections.emptyList())));
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/feedback/events/20/questions")).andExpect(MockMvcResultMatchers.status().isNoContent());
		
		verify(feedbackServiceMock).findEventById(20L);
	}
	
}
