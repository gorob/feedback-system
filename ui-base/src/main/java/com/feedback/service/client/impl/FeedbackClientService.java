package com.feedback.service.client.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.feedback.service.client.IFeedbackClientService;
import com.feedback.service.client.model.Event;

public class FeedbackClientService implements IFeedbackClientService {
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
}
