package com.badenia.feedback.feedbacksystem.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.badenia.feedback.feedbacksystem.exceptions.EntityNotFoundException;
import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class QuestionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	IFeedbackService feedbackServiceMock;

	@Test
	public void testReadAll_NoEventFoundForId20_ReturnsHttpStatusNotFound() throws Exception {

		when(feedbackServiceMock.findEventById(20L)).thenThrow(new EntityNotFoundException(Event.class, "id", "20"));

		mockMvc.perform(get("/v1/feedback/events/20/questions"))
				.andDo(print()).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.apierror.status", is("NOT_FOUND")))
				.andExpect(jsonPath("$.apierror.message", is("Event was not found for parameters {id=20}")))
				.andExpect(jsonPath("$.apierror.debugMessage", isEmptyOrNullString()))
				.andExpect(jsonPath("$.apierror.subErrors", nullValue()));

	}

	@Test
	public void testReadAll_NoQuestionsFound_ReturnHttpStatusNoContent() throws Exception {
		when(feedbackServiceMock.findEventById(20L)).thenReturn(new Event(20L, "Question", Collections.emptyList()));

		mockMvc.perform(get("/v1/feedback/events/20/questions"))
				.andDo(print()).andExpect(status().isNoContent())
				.andExpect(content().string(""));

		verify(feedbackServiceMock).findEventById(20L);
	}

}
