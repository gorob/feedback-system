package com.badenia.feedback.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.badenia.feedback.thymeleaf.ui.model.AnswerTypeTM;
import com.badenia.feedback.thymeleaf.ui.model.UiQuestionTM;

@Controller
public class NewQuestionController {
	
	private static final Logger LOGGER = LogManager.getLogger(NewQuestionController.class);
	
	@GetMapping("/newQuestion")
	public String get(Model model, HttpServletRequest request) {
		
		UiQuestionTM questionTM = new UiQuestionTM();
		
		long questionId = getParameter(request, "questionId");
		long idEvent = getParameter(request, "eventId");
		
		questionTM.setEventId(idEvent);
		//Wurde Param questionId in dem URL mitgegeben, dann ist das ein Update -> Daten weiter geben
		if (questionId > 0) {
			questionTM.setId(questionId);
			//TODO Read question to questionID and put this to the UI over questionTM
			questionTM.setQuestionName("Hurraaaa");
			questionTM.setAnswerTypeId(1L);
		}
		
		LOGGER.debug("eventID: {}, questionID {}", idEvent, questionId);

		List<AnswerTypeTM> answerTypes = new ArrayList<AnswerTypeTM>();
		answerTypes.add(new AnswerTypeTM(1L, "Freetext"));
		answerTypes.add(new AnswerTypeTM(2L, "Smiley"));
		model.addAttribute("questionTM", questionTM);
		model.addAttribute("answerTypes", answerTypes);
		return "newQuestion";
	}
	
	private long getParameter(HttpServletRequest request, String searchParameter) {
		String parameter = request.getParameter(searchParameter);
		if(parameter != null) {
			return Long.parseLong(parameter);
			}
		return 0;
	}

	@PostMapping("/newQuestion")
    public String post(@ModelAttribute UiQuestionTM questionTM) {
		LOGGER.debug("QuestionName: {}, eventId: {}, AnswerTypeId: {}", questionTM.getQuestionName(), questionTM.getEventId(), questionTM.getAnswerTypeId());
		//TODO Question in DB save or update
		return "redirect:/questions?param=" + questionTM.getEventId();
    }

}
