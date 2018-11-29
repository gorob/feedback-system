package com.badenia.feedback.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.badenia.feedback.thymeleaf.model.Event;
import com.badenia.feedback.thymeleaf.model.Question;
import com.badenia.feedback.thymeleaf.service.FeedbackClientService;
import com.badenia.feedback.thymeleaf.service.IFeedbackClientService;

@Controller
public class QuestionsController {
	
IFeedbackClientService serviceReposiroty = new FeedbackClientService();
	
	@GetMapping("/questions")
	public String index(Model model, HttpServletRequest request) {
		List<Event> allEvents = serviceReposiroty.leseAlleEvents();
		String attribute = request.getParameter("param");
		int idEvent = Integer.parseInt(attribute);
		List<Question> allQuestionsToEvent = getQuestionsToEvent(allEvents, idEvent);
		model.addAttribute("questions", allQuestionsToEvent);
		return "questions";
	}

	private List<Question> getQuestionsToEvent(List<Event> allEvents, int eventId) {
		for (Event event : allEvents) {
			if (event.getId() == eventId) {
				return event.getQuestions();
			}
		}
		
		return new ArrayList<Question>();
	}
	
	

}
