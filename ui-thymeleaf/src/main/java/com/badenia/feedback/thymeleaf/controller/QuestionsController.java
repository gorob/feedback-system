package com.badenia.feedback.thymeleaf.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feedback.service.client.model.Question;

@Controller
public class QuestionsController extends AbstractController {

	@GetMapping(path = {"/questions"}, params = "eventId")
	public String index(Model model, @RequestParam(required = true, name = "eventId") Long eventId) {
		List<Question> allQuestionsToEvent = getFeedbackService().leseAlleFragenZuEvent(eventId);
		model.addAttribute("questions", allQuestionsToEvent);
		model.addAttribute("eventId", eventId);
		return "questions";
	}
	
	@GetMapping(path = {"/answer"}, params = {"eventId", "questionId"})
	public String index(Model model, @RequestParam(required = true, name = "eventId") Long eventId, @RequestParam(required = true, name = "questionId") Long questionId) {
		List<Question> allQuestionsToEvent = getFeedbackService().leseAlleFragenZuEvent(eventId);
		model.addAttribute("question", allQuestionsToEvent);
		model.addAttribute("eventId", eventId);
		return "answer";
	}

}
