package com.badenia.feedback.client.service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.Rule;
import org.junit.Test;

import com.badenia.feedback.client.service.model.Event;
import com.badenia.feedback.client.service.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class FeedbackClientServiceTest {
	private FeedbackClientService clientService;
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(9000);
	
	
	@Test
	public void testLeseAlleEvents_NoContent() throws Exception {
		clientService = new FeedbackClientService(wireMockRule.port());
		
		stubFor(get(urlEqualTo("/v1/feedback/events"))
	            .withHeader("Accept", equalTo(MediaType.APPLICATION_JSON))
	            .willReturn(aResponse()
	            .withStatus(Status.NO_CONTENT.getStatusCode())
	            .withHeader("Content-Type", MediaType.APPLICATION_JSON)));

	    List<Event> events = clientService.leseAlleEvents();

	    assertTrue(events.isEmpty());
	}
	
	@Test
	public void testLeseAlleEvents_ContentButNotValidJSON() throws Exception {
		clientService = new FeedbackClientService(wireMockRule.port());
		
		stubFor(get(urlEqualTo("/v1/feedback/events"))
	            .withHeader("Accept", equalTo(MediaType.APPLICATION_JSON))
	            .willReturn(aResponse()
	            .withBody("wrongJSONContent")
	            .withStatus(Status.OK.getStatusCode())
	            .withHeader("Content-Type", MediaType.APPLICATION_JSON)));

	    try {
			clientService.leseAlleEvents();
			fail("Exception expected!");
		} catch (Exception ex) {
			assertEquals("Fehler beim Erstellen Model aus Json!", ex.getMessage());
		}
	}

	@Test
	public void testLeseAlleEvents_OneEvent() throws Exception {
		clientService = new FeedbackClientService(wireMockRule.port());
		
		stubFor(get(urlEqualTo("/v1/feedback/events"))
	            .withHeader("Accept", equalTo(MediaType.APPLICATION_JSON))
	            .willReturn(aResponse()
	            .withBody(createEventAsJSON(createEvent(1L, "eventName", createQuestion(11L, "questionName"))))
	            .withStatus(Status.OK.getStatusCode())
	            .withHeader("Content-Type", MediaType.APPLICATION_JSON)));

	    List<Event> events = clientService.leseAlleEvents();

	    assertEquals(1, events.size());
	    Event event = events.get(0);
		assertEquals(new Long("1"), event.getId());
	    assertEquals("eventName", event.getName());
	    assertEquals(1, event.getQuestions().size());
	    assertEquals(new Long("11"), event.getQuestions().get(0).getId());
	    assertEquals("questionName", event.getQuestions().get(0).getQuestionName());
	}
	
	@Test
	public void testLeseAlleEvents_MoreThanOneEvent() throws Exception {
		clientService = new FeedbackClientService(wireMockRule.port());
		
		Event event1 = createEvent(1L, "eventName1", createQuestion(11L, "questionName1"));
		Event event2 = createEvent(2L, "eventName2", createQuestion(22L, "questionName2"));
		
		stubFor(get(urlEqualTo("/v1/feedback/events"))
	            .withHeader("Accept", equalTo(MediaType.APPLICATION_JSON))
	            .willReturn(aResponse()
	            .withBody(createEventAsJSON(event1, event2))
	            .withStatus(Status.OK.getStatusCode())
	            .withHeader("Content-Type", MediaType.APPLICATION_JSON)));

	    List<Event> events = clientService.leseAlleEvents();

	    assertEquals(2, events.size());
	    
	    Event event = events.get(0);
		assertEquals(new Long("1"), event.getId());
	    assertEquals("eventName1", event.getName());
	    assertEquals(1, event.getQuestions().size());
	    assertEquals("questionName1", event.getQuestions().get(0).getQuestionName());
	    assertEquals(new Long("11"), event.getQuestions().get(0).getId());
	    
	    event = events.get(1);
		assertEquals(new Long("2"), event.getId());
	    assertEquals("eventName2", event.getName());
	    assertEquals(1, event.getQuestions().size());
	    assertEquals("questionName2", event.getQuestions().get(0).getQuestionName());
	    assertEquals(new Long("22"), event.getQuestions().get(0).getId());
	}
	
	private String createEventAsJSON(Event... event) {
		ObjectMapper mapper = new ObjectMapper();
        
        try {
			return mapper.writeValueAsString(Arrays.asList(event));
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
	private Event createEvent(long id, String eventName, Question... question) {
		return new Event(id, eventName, Arrays.asList(question));
	}
	
	private Question createQuestion(long id, String question) {
		return new Question(id, question);
	}

}
