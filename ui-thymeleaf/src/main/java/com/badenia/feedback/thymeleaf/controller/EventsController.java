package com.badenia.feedback.thymeleaf.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.feedback.service.client.IFeedbackClientService;
import com.feedback.service.client.impl.FeedbackClientService;
import com.feedback.service.client.model.Event;

import lombok.Getter;

@Getter
@Controller
public class EventsController {

	IFeedbackClientService serviceReposiroty = new FeedbackClientService();
	private List<Event> allEvents;

	public EventsController() {
		this.allEvents = serviceReposiroty.leseAlleEvents();
	}

	@GetMapping("/events")
	public String events(Model model) {
		model.addAttribute("events", getAllEvents());
		return "events";
	}

}