package com.badenia.feedback.feedbacksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badenia.feedback.feedbacksystem.repository.model.QuestionOptionTableModel;

public interface QuestionOptionRepository extends JpaRepository<QuestionOptionTableModel, Long> {

}
