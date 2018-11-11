package com.badenia.feedback.client.service;

import java.util.List;

import com.badenia.feedback.client.service.model.Event;

public interface IFeedbackClientService {
	List<Event> leseAlleEvents();
}
