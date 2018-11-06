package com.badenia.feedback.feedbacksystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.badenia.feedback.feedbacksystem.controller.model.EventTM;
import com.badenia.feedback.feedbacksystem.repository.EventRepository;
import com.badenia.feedback.feedbacksystem.repository.model.EventTableModel;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
@RequestMapping("/v1/feedback/events")
@Getter(AccessLevel.PROTECTED)
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<EventTM>> getEvents() {
		List<EventTableModel> events = new ArrayList<>();
		getEventRepository().findAll().forEach(events::add);
		if (events.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(events.stream().map(this::map).collect(Collectors.toList()));
		}
	}

	protected EventTM map(EventTableModel model) {
		return new EventTM(model.getId(), model.getName(), model.getDescription(), model.getStart(), model.getEnd());
	}

}
