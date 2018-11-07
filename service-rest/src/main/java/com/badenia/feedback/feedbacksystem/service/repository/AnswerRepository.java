package com.badenia.feedback.feedbacksystem.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badenia.feedback.feedbacksystem.service.repository.model.AnswerTableModel;

public interface AnswerRepository extends JpaRepository<AnswerTableModel, Long> {

}
