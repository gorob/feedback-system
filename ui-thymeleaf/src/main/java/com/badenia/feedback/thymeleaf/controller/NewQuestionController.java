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
import org.springframework.web.bind.annotation.RequestParam;

import com.badenia.feedback.thymeleaf.ui.model.AnswerTypeTM;
import com.badenia.feedback.thymeleaf.ui.model.UiQuestionTM;
import com.feedback.service.client.model.Question;

@Controller
public class NewQuestionController extends AbstractController {
	
	private static final Logger LOGGER = LogManager.getLogger(NewQuestionController.class);
	
	@GetMapping(path = {"/newQuestion"}, params = {"eventId"})
	public String get(Model model, @RequestParam(required = true, name = "eventId") Long eventId) {
		
		UiQuestionTM questionTM = new UiQuestionTM();
		
		questionTM.setEventId(eventId);
		
		LOGGER.debug("eventID: {}, questionID {}", eventId);

		List<AnswerTypeTM> answerTypes = new ArrayList<AnswerTypeTM>();
		answerTypes.add(new AnswerTypeTM(1L, "Freetext"));
		answerTypes.add(new AnswerTypeTM(2L, "Smiley"));
		model.addAttribute("questionTM", questionTM);
		model.addAttribute("answerTypes", answerTypes);
		return "newQuestion";
	}
	
	@GetMapping(path = {"/newQuestion"}, params = {"eventId", "questionId"})
	public String get(Model model, @RequestParam(required = true, name = "eventId") Long eventId, @RequestParam(required = true, name = "questionId") Long questionId) {
		
		UiQuestionTM questionTM = new UiQuestionTM();
		questionTM.setEventId(eventId);

		//Wurde Param questionId in dem URL mitgegeben, dann ist das ein Update -> Daten weiter geben
		if (questionId > 0) {
			Question question = getFeedbackService().readQuestion(eventId, questionId);

			questionTM.setId(questionId);
			questionTM.setQuestionName(question.getQuestionName());
			questionTM.setAnswerTypeId(1L);
		}
		
		LOGGER.info("eventID: {}, questionID {}", eventId, questionId);

		List<AnswerTypeTM> answerTypes = new ArrayList<AnswerTypeTM>();
		answerTypes.add(new AnswerTypeTM(1L, "Freetext"));
		answerTypes.add(new AnswerTypeTM(2L, "Smiley"));
		model.addAttribute("questionTM", questionTM);
		model.addAttribute("answerTypes", answerTypes);
		return "newQuestion";
	}

	@PostMapping("/newQuestion")
    public String post(@ModelAttribute UiQuestionTM questionTM) {
		LOGGER.info("QuestionId: {}, QuestionName: {}, eventId: {}, AnswerTypeId: {}", questionTM.getId(), questionTM.getQuestionName(), questionTM.getEventId(), questionTM.getAnswerTypeId());
		Question questionToSave = new Question(questionTM.getId(), questionTM.getQuestionName(), "THREE_SMILEYS");
//		getFeedbackService().saveQuestion(questionTM.getEventId(), questionToSave);
		LOGGER.info("Question to Save: {}", questionToSave);
		return "redirect:/questions?eventId=" + questionTM.getEventId();
    }

}
