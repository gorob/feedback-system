package com.badenia.feedback.thymeleaf.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.badenia.feedback.thymeleaf.ui.model.AnswerForm;
import com.feedback.service.client.model.Event;
import com.feedback.service.client.model.Question;

@Controller
public class QuestionsController extends AbstractController {
	
	private static final Logger LOGGER = LogManager.getLogger(QuestionsController.class);

	@GetMapping(path = {"/questions"}, params = "eventId")
	public String index(Model model, @RequestParam(required = true, name = "eventId") Long eventId) {
		List<Question> allQuestionsToEvent = getFeedbackService().leseAlleFragenZuEvent(eventId);
		model.addAttribute("questions", allQuestionsToEvent);
		model.addAttribute("eventId", eventId);
		return "questions";
	}
	
	@GetMapping(path = {"/answer"}, params = {"eventId"})
	public String answer(Model model, @RequestParam(required = true, name = "eventId") Long eventId) {
		Event eventById = getFeedbackService().getEventById(eventId);
		List<Question> allQuestionsToEvent = getFeedbackService().leseAlleFragenZuEvent(eventId);
		model.addAttribute("questions", allQuestionsToEvent);
		model.addAttribute("eventId", eventId);
		model.addAttribute("eventName", eventById.getName());
		model.addAttribute("answerForm", new AnswerForm(eventId, ""));
		return "answer";
	}
	
	@PostMapping(path = {"/answer"})
	public String answerPost(@ModelAttribute(value="answerForm") AnswerForm answer) {
		LOGGER.info("Answer obj: {}", answer.toString());
		LOGGER.info("AnswerForm submitted!");
		return "redirect:/";
	}

}
