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
		UIEventTM eventTM = new UIEventTM();

		long idEvent = getParameter(request, "eventId");

		// Wurde Param questionId in dem URL mitgegeben, dann ist das ein Update ->
		// Daten weiter geben
		if (idEvent > 0) {
			eventTM.setId(idEvent);
			// TODO Read question to questionID and put this to the UI over questionTM
			eventTM.setEventName("EventUpdate");
		}

		model.addAttribute("eventTM", eventTM);
		return "newEvent";
	}

	@PostMapping("/newEvent")
	public String post(@ModelAttribute UIEventTM eventTM) {
		System.out.println(eventTM.getEventName());
		// TODO Event in DB speichern
		return "redirect:/events";
	}

	private long getParameter(HttpServletRequest request, String searchParameter) {
		String parameter = request.getParameter(searchParameter);
		if (parameter != null) {
			return Long.parseLong(parameter);
		}
		return 0;
	}

}
