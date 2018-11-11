package com.badenia.feedback.client.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.badenia.feedback.client.service.model.Event;

public class FeedbackClientServiceIntegrationTest {
	private FeedbackClientService clientService;
		
	@Test
	@Ignore
	public void testLeseAlleEvents() throws Exception {
		clientService = new FeedbackClientService();
		
	    List<Event> events = clientService.leseAlleEvents();

	    assertEquals(2, events.size());
	    
	    Event event = events.get(0);
		assertEquals(new Long("1"), event.getId());
	    assertEquals("Tägliche Essen-Umfrage", event.getName());
	    assertEquals(1, event.getQuestions().size());
	    assertEquals(new Long("1"), event.getQuestions().get(0).getId());
	    assertEquals("Wie hat Ihnen heute das Essen geschmeckt?", event.getQuestions().get(0).getQuestionName());
	    
	    event = events.get(1);
		assertEquals(new Long("2"), event.getId());
	    assertEquals("Freespace 2018", event.getName());
	    assertEquals(8, event.getQuestions().size());
	    assertEquals(new Long("2"), event.getQuestions().get(0).getId());
	    assertEquals("Wie fanden Sie generell den Ablauf des \"Free Space\" (Freitag \"Teamfindung\" / Montag \"Free Space und Review\")?", event.getQuestions().get(0).getQuestionName());
	    assertEquals(new Long("3"), event.getQuestions().get(1).getId());
	    assertEquals("Wie fanden Sie die Information im Vorfeld (E-Mail und Vorstellung in Gruppenrunden)?", event.getQuestions().get(1).getQuestionName());
	    assertEquals(new Long("4"), event.getQuestions().get(2).getId());
	    assertEquals("Wie fanden Sie die Teilveranstaltung \"Teamfindung/Organisatorisches\" am Freitag?", event.getQuestions().get(2).getQuestionName());
	    assertEquals(new Long("5"), event.getQuestions().get(3).getId());
	    assertEquals("Wie fanden Sie die Teilveranstaltung \"Free Space - Let's do it!\" am Montag?", event.getQuestions().get(3).getQuestionName());
	    assertEquals(new Long("6"), event.getQuestions().get(4).getId());
	    assertEquals("Wie fanden Sie die Teilveranstaltung \"Review\" am Montag?", event.getQuestions().get(4).getQuestionName());
	    assertEquals(new Long("7"), event.getQuestions().get(5).getId());
	    assertEquals("Würden Sie an weiteren \"Free Space\" - Veranstaltungen genau so in diesen Rahmen (Teamfindung; Free Space; Review) teilnehmen?", event.getQuestions().get(5).getQuestionName());
	    assertEquals(new Long("8"), event.getQuestions().get(6).getId());
	    assertEquals("Würden Sie gerne Ihre Ideen in einem anderen Rahmen umsetzen?", event.getQuestions().get(6).getQuestionName());
	    assertEquals(new Long("9"), event.getQuestions().get(7).getId());
	    assertEquals("Was fanden Sie gut bzw. was würden Sie verbessern wollen?", event.getQuestions().get(7).getQuestionName());
	}

}
