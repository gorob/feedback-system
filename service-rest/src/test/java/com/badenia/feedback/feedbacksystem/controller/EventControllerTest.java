package com.badenia.feedback.feedbacksystem.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.badenia.feedback.feedbacksystem.exceptions.EntityNotFoundException;
import com.badenia.feedback.feedbacksystem.service.IFeedbackService;
import com.badenia.feedback.feedbacksystem.service.model.Event;

import lombok.AccessLevel;
import lombok.Getter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Getter(AccessLevel.PROTECTED)
@AutoConfigureJsonTesters
public class EventControllerTest {

	@MockBean
	IFeedbackService feedbackServiceMock;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	JacksonTester<Event> json;

	@Test
	public void testGetEvents_NoEventsExists_ReturnsHttpStatusNoContent() throws Exception {

		when(getFeedbackServiceMock().findEvents()).thenReturn(new ArrayList<>());

		getMockMvc()
				.perform(MockMvcRequestBuilders.get("/v1/feedback/events")
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

		verify(getFeedbackServiceMock()).findEvents();
	}

	/**
	 * Test method for
	 * {@link com.badenia.feedback.feedbacksystem.controller.EventController#createEvent(com.badenia.feedback.feedbacksystem.service.model.Event)}.
	 */
	@Test
	public void testCreateEvent() throws Exception {

		when(getFeedbackServiceMock().saveEvent(Event.builder().id(-1L).name("Event").build())).thenReturn(20L);

		String jsonValue = json.write(Event.builder().id(-1L).name("Event").build()).getJson();
		
		getMockMvc()
				.perform(post("/v1/feedback/events").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(jsonValue))
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.header().stringValues("location", "http://localhost/v1/feedback/events/20"));

		verify(getFeedbackServiceMock()).saveEvent(Event.builder().id(-1L).name("Event").build());

	}
	
	@Test
	public void testCreateEvent_ProvideIdNull_CallServiceWithNegativeId() throws Exception {

		when(getFeedbackServiceMock().saveEvent(Event.builder().id(-1L).name("Event").build())).thenReturn(20L);

		String jsonValue = json.write(Event.builder().id(null).name("Event").build()).getJson();
		
		getMockMvc()
				.perform(post("/v1/feedback/events").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(jsonValue))
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.header().stringValues("location", "http://localhost/v1/feedback/events/20"));

		verify(getFeedbackServiceMock()).saveEvent(Event.builder().id(-1L).name("Event").build());

	}

	/**
	 * Test method for
	 * {@link com.badenia.feedback.feedbacksystem.controller.EventController#getEvent(long)}.
	 */
	@Test
	public void testGetEvent() throws Exception {
		when(getFeedbackServiceMock().findEventById(20L))
				.thenThrow(new EntityNotFoundException(Event.class, "id", "20"));

		getMockMvc().perform(get("/v1/feedback/events/20")).andDo(print()).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.apierror.status", is("NOT_FOUND")))
				.andExpect(jsonPath("$.apierror.message", is("Event was not found for parameters {id=20}")))
				.andExpect(jsonPath("$.apierror.debugMessage", isEmptyOrNullString()))
				.andExpect(jsonPath("$.apierror.subErrors", nullValue()));
	}

	/**
	 * Test method for {@link com.badenia.feedback.feedbacksystem.controller.EventController#updateEvent(java.lang.Long, com.badenia.feedback.feedbacksystem.service.model.Event)}.
	 */
	@Test
	public void testUpdateEvent() throws Exception {
		when(getFeedbackServiceMock().saveEvent(Event.builder().id(20L).name("Event").build())).thenReturn(20L);

		String jsonValue = json.write(Event.builder().id(-1L).name("Event").build()).getJson();
		
		getMockMvc()
				.perform(put("/v1/feedback/events/20").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(jsonValue))
				.andDo(print()).andExpect(status().isNoContent());

		verify(getFeedbackServiceMock()).saveEvent(Event.builder().id(20L).name("Event").build());
	}

}
