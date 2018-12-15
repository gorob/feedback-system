package com.badenia.feedback.thymeleaf.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.badenia.feedback.thymeleaf.ui.model.UIEventTM;
import com.feedback.service.client.model.Event;

@Controller
public class NewEventController extends AbstractController {

	private static final Logger LOGGER = LogManager.getLogger(NewEventController.class);

	@GetMapping("/newEvent")
	public String get(Model model, HttpServletRequest request) {
		UIEventTM eventTM = new UIEventTM();

		long idEvent = getParameter(request, "eventId");

		// Wurde Param questionId in dem URL mitgegeben, dann ist das ein Update ->
		// Daten weiter geben
		if (idEvent > 0) {
			// TODO Read question to questionID and put this to the UI over questionTM
			Event event = getFeedbackService().getEventById(idEvent);
			eventTM.setEventName(event.getName());
			eventTM.setId(event.getId());
		}

		model.addAttribute("eventTM", eventTM);
		return "newEvent";
	}

	@PostMapping("/newEvent")
	public String post(@ModelAttribute UIEventTM eventTM) {
		LOGGER.traceEntry("Parameter {}", eventTM);
		Event event = new Event(eventTM.getId(), eventTM.getEventName());
		getFeedbackService().saveEvent(event);
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
