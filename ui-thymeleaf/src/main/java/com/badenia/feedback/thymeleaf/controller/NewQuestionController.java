package com.badenia.feedback.thymeleaf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.badenia.feedback.thymeleaf.ui.model.UiQuestionTM;

@Controller
public class NewQuestionController {
	
	@GetMapping("/newQuestion")
	public String get(Model model, HttpServletRequest request) {
		String parameter = request.getParameter("eventId");
		int idEvent = Integer.parseInt(parameter);
		UiQuestionTM questionTM = new UiQuestionTM();
		questionTM.setEventId((long) idEvent);
		model.addAttribute("questionTM", questionTM);
		return "snipperNewQuestion";
	}
	
	@PostMapping("/newQuestion")
    public String post(@ModelAttribute UiQuestionTM questionTM) {
		System.out.println(questionTM.getQuestionName());
		System.out.println("Eventid: " + questionTM.getEventId());
		//TODO Question in DB speichern die questions lese alle questions zu dem Event
		return "redirect:/questions?param=" + questionTM.getEventId();
    }

}
