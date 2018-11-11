package com.badenia.feedback.client.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.badenia.feedback.client.service.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		return readListFromJson(get("events"), Event.class);
	}
	
	private WebTarget getWebTarget(String path) {
		Client client = ClientBuilder.newClient();
		return client.target(getBasePath()).path(path);		
	}
	
	private Response get(String path) {
		return getWebTarget(path).request(MediaType.APPLICATION_JSON_TYPE).get();
	}
	
	private <T> List<T> readListFromJson(Response response, Class<T> clazz) {
		if (isNoContent(response)) {
			return new ArrayList<>();
		}

		String entityString = response.readEntity(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(entityString,  objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
		} catch (IOException e) {
			throw new RuntimeException("Fehler beim Erstellen Model aus Json!", e);
		}
	}
	
	private boolean isNoContent(Response response) {
		return Status.fromStatusCode(response.getStatus()).equals(Status.NO_CONTENT);
	}
}
