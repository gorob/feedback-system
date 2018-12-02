package com.badenia.feedback.thymeleaf.ui.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UiQuestionTM {

	private Long id;

	private String questionName;

	private Long eventId;
	
	private String answerFreeText;
	
	private String answerSmyles;
	
	private List<String> answerTypes;

}
