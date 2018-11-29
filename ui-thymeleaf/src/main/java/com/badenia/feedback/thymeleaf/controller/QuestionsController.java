package com.badenia.feedback.thymeleaf.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feedback.service.client.IFeedbackClientService;
import com.feedback.service.client.impl.FeedbackClientService;
import com.feedback.service.client.model.Event;
import com.feedback.service.client.model.Question;

@Controller
public class QuestionsController {
	
IFeedbackClientService serviceReposiroty = new FeedbackClientService();
	
	@GetMapping("/questions")
	public String index(Model model, @RequestParam(defaultValue="1") int idEvent) {
		List<Event> allEvents = serviceReposiroty.leseAlleEvents();
		List<Question> allQuestionsToEvent = getQuestionsToEvent(allEvents, idEvent);
		model.addAttribute("questions", allQuestionsToEvent);
		return "questions";
	}

	private List<Question> getQuestionsToEvent(List<Event> allEvents, int eventId) {
		return allEvents.get(eventId).getQuestions();
	}
	
	

}
