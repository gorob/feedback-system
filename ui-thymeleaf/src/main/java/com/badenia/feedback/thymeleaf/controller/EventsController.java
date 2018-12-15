package com.badenia.feedback.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventsController extends AbstractController {

	@GetMapping("/events")
	public String events(Model model) {
		model.addAttribute("events", getFeedbackService().leseAlleEvents());
		return "events";
	}

}