package com.badenia.feedback.thymeleaf.service;

import java.util.List;

import com.badenia.feedback.thymeleaf.model.Event;

public interface IFeedbackClientService {
	
	List<Event> leseAlleEvents();

}
