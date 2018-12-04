package com.badenia.feedback.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.badenia.feedback.thymeleaf.ui.model.AnswerTypeTM;
import com.badenia.feedback.thymeleaf.ui.model.UiQuestionTM;

@Controller
public class NewQuestionController {
	
	@GetMapping("/newQuestion")
	public String get(Model model, HttpServletRequest request) {
		UiQuestionTM questionTM = new UiQuestionTM();
		String parameter = request.getParameter("eventId");
		if(parameter != null) {
			long idEvent = Long.parseLong(parameter);
			questionTM.setEventId(idEvent);
			}
		List<AnswerTypeTM> answerTypes = new ArrayList<AnswerTypeTM>();
		answerTypes.add(new AnswerTypeTM(1L, "Freetext"));
		answerTypes.add(new AnswerTypeTM(2L, "Smiley"));
		model.addAttribute("questionTM", questionTM);
		model.addAttribute("answerTypes", answerTypes);
		return "snipperNewQuestion";
	}
	
	@PostMapping("/newQuestion")
    public String post(@ModelAttribute UiQuestionTM questionTM) {
		System.out.println(questionTM.getQuestionName());
		System.out.println("Eventid: " + questionTM.getEventId());
		System.out.println("AnswerTypeId: " + questionTM.getAnswerTypeId());
		//TODO Question in DB speichern die questions lese alle questions zu dem Event
		return "redirect:/questions?param=" + questionTM.getEventId();
    }

}
