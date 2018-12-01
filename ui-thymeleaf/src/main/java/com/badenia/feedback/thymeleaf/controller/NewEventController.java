package com.badenia.feedback.thymeleaf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.badenia.feedback.thymeleaf.ui.model.UIEventTM;

@Controller
public class NewEventController {
	
	@GetMapping("/newEvent")
	public String get(Model model, HttpServletRequest request) {
		model.addAttribute("eventTM", new UIEventTM());
		return "newEvent";
	}
	
	@PostMapping("/newEvent")
    public String post(@ModelAttribute UIEventTM eventTM) {
		System.out.println(eventTM.getEventName());
		//TODO Event in DB speichern
		return "redirect:/events";
    }

}
