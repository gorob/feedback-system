package com.badenia.feedback.feedbacksystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.badenia.feedback.feedbacksystem.repository.model.EventTableModel;

@Repository
public interface EventRepository extends CrudRepository<EventTableModel, Long> {

}
