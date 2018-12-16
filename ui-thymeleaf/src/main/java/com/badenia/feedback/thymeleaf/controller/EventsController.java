package com.badenia.feedback.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventsController extends AbstractController {

	@GetMapping("/events")
	public String events(Model model) {
		model.addAttribute("events", getFeedbackService().leseAlleEvents());
		return "events";
	}
	
	@GetMapping("/deleteEvent")
	public String delete(@RequestParam("eventId") Long eventId, Model model) {
		getFeedbackService().deleteEvent(eventId);
		model.addAttribute("events", getFeedbackService().leseAlleEvents());
		return "events";
	}

}