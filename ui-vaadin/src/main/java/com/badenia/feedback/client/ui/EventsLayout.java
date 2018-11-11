package com.badenia.feedback.client.ui;

import java.util.List;

import com.badenia.feedback.client.service.FeedbackClientService;
import com.badenia.feedback.client.service.IFeedbackClientService;
import com.badenia.feedback.client.service.model.Event;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("Feedbacksystem")
@StyleSheet("styles/format.css")
public class EventsLayout extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private H2 header;
    private Grid<Event> grid;

    private IFeedbackClientService service;
    
    public EventsLayout() {
    	this.header = new H2("Übersicht Events");
		this.grid = new Grid<Event>();
		this.service = new FeedbackClientService();
		
		this.initView();
		this.initTable();
		this.updateTableContent();
	}
    
    public Grid<Event> getGrid() {
		return grid;
	}
    
    public H2 getHeader() {
		return header;
	}
    
    public IFeedbackClientService getService() {
		return service;
	}
    
    private void initView() {
        addClassName("categories-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }
    
	private void initTable() {
        VerticalLayout container = new VerticalLayout();
        container.setClassName("view-container");
        container.setAlignItems(Alignment.STRETCH);

        getGrid().addColumn(Event::getName).setHeader("Event").setWidth("8em").setResizable(true);
        getGrid().setSelectionMode(SelectionMode.NONE);

        container.add(getHeader(), getGrid());
        add(container);
    }
	
    private void updateTableContent() {
        List<Event> events = getService().leseAlleEvents();
        getGrid().setItems(events);
    }
}
