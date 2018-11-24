package com.badenia.feedback.thymeleaf.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class EventTest {

	@Test
	public void testGetNumberOfQuestionsStr() throws Exception {
		Event event = new Event();
		assertEquals("[0 Fragen]", event.getNumberOfQuestionsStr());
		
		event.setQuestions(new ArrayList<Question>());
		assertEquals("[0 Fragen]", event.getNumberOfQuestionsStr());

		event.getQuestions().add(new Question());
		assertEquals("[1 Frage]", event.getNumberOfQuestionsStr());

		event.getQuestions().add(new Question());
		assertEquals("[2 Fragen]", event.getNumberOfQuestionsStr());

		event.getQuestions().add(new Question());
		assertEquals("[3 Fragen]", event.getNumberOfQuestionsStr());
	}

}
