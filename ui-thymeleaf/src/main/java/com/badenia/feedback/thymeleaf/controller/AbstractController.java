package com.badenia.feedback.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.feedback.service.client.IFeedbackClientService;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PROTECTED)
abstract class AbstractController {

	@Autowired private IFeedbackClientService feedbackService;
	
	public AbstractController() {
	}

}
