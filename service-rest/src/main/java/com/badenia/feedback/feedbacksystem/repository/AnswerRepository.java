package com.badenia.feedback.feedbacksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badenia.feedback.feedbacksystem.repository.model.AnswerTableModel;

public interface AnswerRepository extends JpaRepository<AnswerTableModel, Long> {

}
