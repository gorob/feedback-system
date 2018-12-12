package com.badenia.feedback.feedbacksystem.service.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class Answer {

	@JsonProperty("questionId")
	@NotNull
	private Long questionId;
	@NotNull
	@JsonProperty("optionId")
	private Long optionId;

	@JsonProperty("remark")
	private String remark;

	@JsonProperty("answeredAt")
	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime answeredAt;

}
