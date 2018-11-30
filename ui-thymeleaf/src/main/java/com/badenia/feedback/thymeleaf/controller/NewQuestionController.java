package com.badenia.feedback.thymeleaf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.feedback.service.client.model.Question;

@Controller
public class NewQuestionController {

	@GetMapping("/newQuestion")
	public String get(Model model, HttpServletRequest request) {
		model.addAttribute(new Question());
		return "snipperNewQuestion";
	}
	
	@PostMapping("/newQuestion")
    public String post(@ModelAttribute Question question) {
		System.out.println(question.getQuestionName());
		return "redirect:/events";
    }

}
