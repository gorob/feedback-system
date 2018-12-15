package com.feedback.service.client.impl;

import java.net.URI;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.feedback.service.client.IFeedbackClientService;
import com.feedback.service.client.model.Event;
import com.feedback.service.client.model.Option;
import com.feedback.service.client.model.Question;

public class FeedbackClientService implements IFeedbackClientService {

	private static final Logger LOGGER = LogManager.getLogger(FeedbackClientService.class);

	private static final String SERVER = "localhost";
	private static final String BASE_URL = "v1/feedback";
	private static final int PORT = 8080;

	private String basePath;

	public FeedbackClientService() {
		this(PORT);
	}

	protected FeedbackClientService(int port) {
		this.basePath = "http://" + SERVER + ":" + port + "/" + BASE_URL;
	}

	protected String getBasePath() {
		return basePath;
	}

	@Override
	public List<Event> leseAlleEvents() {
		ResponseEntity<List<Event>> responseEntity = new RestTemplate().exchange(this.basePath + "/events",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Event>>() {
				});
		return responseEntity.getBody();
	}

	@Override
	public List<Question> leseAlleFragenZuEvent(Long eventId) {
		ResponseEntity<List<Question>> response = new RestTemplate().exchange(
				this.basePath + "/events/" + eventId.toString() + "/questions", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Question>>() {
				});
		return response.getBody();
	}

	@Override
	public List<Option> leseAlleOptionenZuEinerFrage(Long eventId, Long questionId) {
		ResponseEntity<List<Option>> response = new RestTemplate().exchange(
				this.basePath + "/events/" + eventId.toString() + "/questions/" + questionId.toString() + "/options",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Option>>() {
				});
		return response.getBody();
	}

	@Override
	public Event saveEvent(Event event) {
		if (event.getId() != null) {
			new RestTemplate().put(this.basePath + "/events/" + event.getId(), event);
			LOGGER.info("updated Event: {}", event);
		} else {
			URI uri4CreatedEvent = new RestTemplate().postForLocation(this.basePath + "/events", event);
			LOGGER.info("created Event: {}", uri4CreatedEvent);
		}
		return event;
	}

	@Override
	public Event getEventById(long id) {
		ResponseEntity<Event> response = new RestTemplate().exchange(this.basePath + "/events/" + id, HttpMethod.GET,
				null, new ParameterizedTypeReference<Event>() {
				});
		return response.getBody();
	}

	@Override
	public Question readQuestion(Long eventId, Long questionId) {
		ResponseEntity<Question> response = new RestTemplate().exchange(this.basePath + "/events/" + eventId 
				+ "/questions/" + questionId, HttpMethod.GET,
				null, new ParameterizedTypeReference<Question>() {
				});
		return response.getBody();
	}

	@Override
	public Question saveQuestion(Long eventId, Question question) {
		if (question.getId() != null) {
			new RestTemplate().put(this.basePath + "/events/" + eventId + "/questions/" + question.getId(), question);
			LOGGER.info("updated Question: {}", question);
		} else {
			URI uri4CreatedEvent = new RestTemplate().postForLocation(this.basePath + "/events/" + eventId + "/questions", question);
			LOGGER.info("created Question: {}", uri4CreatedEvent);
		}
		return question;
	}
}
