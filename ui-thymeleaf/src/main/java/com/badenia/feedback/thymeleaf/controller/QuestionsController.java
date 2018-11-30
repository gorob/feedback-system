package com.badenia.feedback.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.feedback.service.client.IFeedbackClientService;
import com.feedback.service.client.impl.FeedbackClientService;
import com.feedback.service.client.model.Event;
import com.feedback.service.client.model.Question;

@Controller
public class QuestionsController {

	private IFeedbackClientService serviceReposiroty = new FeedbackClientService();

	@GetMapping("/questions")
	public String index(Model model, HttpServletRequest request) {
		List<Event> allEvents = serviceReposiroty.leseAlleEvents();
		String attribute = request.getParameter("param");
		int idEvent = Integer.parseInt(attribute);
		List<Question> allQuestionsToEvent = getQuestionsToEvent(allEvents, idEvent);
		model.addAttribute("questions", allQuestionsToEvent);
		model.addAttribute("eventId", attribute);
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
