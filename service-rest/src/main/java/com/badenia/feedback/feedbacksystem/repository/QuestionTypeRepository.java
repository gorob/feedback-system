package com.badenia.feedback.feedbacksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badenia.feedback.feedbacksystem.repository.model.QuestionTypeTableModel;

public interface QuestionTypeRepository extends JpaRepository<QuestionTypeTableModel, Long> {

}
