package com.feedback.service.client;

import java.util.List;

import com.feedback.service.client.model.Event;
import com.feedback.service.client.model.Option;
import com.feedback.service.client.model.Question;

public interface IFeedbackClientService {
	
	List<Event> leseAlleEvents();
	
	Event saveEvent(Event event);
	
	Event getEventById(long id);
	
	List<Question> leseAlleFragenZuEvent(Long eventId);

	List<Option> leseAlleOptionenZuEinerFrage(Long eventId, Long questionId);
	

}
