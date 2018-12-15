package com.badenia.feedback.thymeleaf.controller;

import com.feedback.service.client.IFeedbackClientService;
import com.feedback.service.client.impl.FeedbackClientService;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PROTECTED)
abstract class AbstractController {

	private IFeedbackClientService feedbackService;
	
	public AbstractController() {
		feedbackService = new FeedbackClientService();
	}

}
