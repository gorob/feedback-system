package com.badenia.feedback.feedbacksystem.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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

import com.badenia.feedback.feedbacksystem.service.IFeedbackService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EventController.class)
public class EventControllerTest {
	
	@MockBean
	IFeedbackService feedbackService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testGetEvents_NoEventsExists_ReturnsHttpStatusNoContent() throws Exception {
		
		when(feedbackService.findEvents()).thenReturn(new  ArrayList<>());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/feedback/events").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(MockMvcResultMatchers.status().isNoContent());
		
		verify(feedbackService).findEvents();
	}

}
