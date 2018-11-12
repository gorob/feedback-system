package com.badenia.feedback.client.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.badenia.feedback.client.service.model.Event;

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
		ResponseEntity<List<Event>> responseEntity = new RestTemplate().exchange(this.basePath + "/events", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Event>>() {
				});
		return responseEntity.getBody();
	}

//	private WebTarget getWebTarget(String path) {
//		Client client = ClientBuilder.newClient();
//		return client.target(getBasePath()).path(path);		
//	}
//	
//	private Response get(String path) {
//		return getWebTarget(path).request(MediaType.APPLICATION_JSON_TYPE).get();
//	}
//	
//	private <T> List<T> readListFromJson(Response response, Class<T> clazz) {
//		if (isNoContent(response)) {
//			return new ArrayList<>();
//		}
//
//		String entityString = response.readEntity(String.class);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			return objectMapper.readValue(entityString,  objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
//		} catch (IOException e) {
//			throw new RuntimeException("Fehler beim Erstellen Model aus Json!", e);
//		}
//	}
//	
//	private boolean isNoContent(Response response) {
//		return Status.fromStatusCode(response.getStatus()).equals(Status.NO_CONTENT);
//	}
}
