package com.badenia.feedback.thymeleaf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.badenia.feedback.thymeleaf.model.Event;
import com.badenia.feedback.thymeleaf.model.Question;
import com.badenia.feedback.thymeleaf.service.FeedbackClientService;
import com.badenia.feedback.thymeleaf.service.IFeedbackClientService;

import lombok.Getter;

@Getter
@Controller
public class EventsController {
	
	IFeedbackClientService serviceReposiroty = new FeedbackClientService();
	private List<Event> allEvents;
	
	public EventsController() {
		this.allEvents = serviceReposiroty.leseAlleEvents();
	}
	
	@ModelAttribute(value="event")
	public Event newEvent() {
		return new Event();
	}
	
	@GetMapping("/events")
	public ModelAndView index(Model model, HttpServletRequest request) {
		
		String view = "events";
		model.addAttribute("events", getAllEvents());
	    return new ModelAndView(view, "command", model);
		
	}
	
	@PostMapping("/events")
	public View getQuestionsToEvent(Model model, @ModelAttribute("event") Event event) {
		System.out.println(event.getId());
		List<Question> allQuestionsToEvent = getQuestionsToEventPrivate(event);
		model.addAttribute("questions", allQuestionsToEvent);
		return new RedirectView("/questions");
	}
	
	private List<Question> getQuestionsToEventPrivate(Event event) {
		return event.getQuestions();
	}
}