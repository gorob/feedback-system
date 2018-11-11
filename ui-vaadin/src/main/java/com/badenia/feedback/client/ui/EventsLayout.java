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

	private VerticalLayout tableLayout;

    private IFeedbackClientService service;
    
    public EventsLayout() {
		this.service = new FeedbackClientService();
		
		this.initView();
		
		this.tableLayout = this.createTableLayout();
		add(this.tableLayout);
		
		this.updateTableContent();
	}
    
    private VerticalLayout getTableLayout() {
		return tableLayout;
	}
    
    private H2 getHeader() {
    	return (H2)getTableLayout().getComponentAt(0);
    }

    @SuppressWarnings("unchecked")
    private Grid<Event> getGrid() {
		return (Grid<Event>)getTableLayout().getComponentAt(1);
	}
    
    
    public IFeedbackClientService getService() {
		return service;
	}
    
    private void initView() {
        addClassName("categories-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }
    
	private VerticalLayout createTableLayout() {
		H2 header = new H2("Übersicht Events");
		
		Grid<Event> grid = new Grid<Event>();
		grid.addColumn(Event::getName).setHeader("Event").setWidth("8em").setResizable(true);
		grid.setSelectionMode(SelectionMode.NONE);
		
        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setClassName("view-container");
        tableLayout.setAlignItems(Alignment.STRETCH);
        tableLayout.add(header, grid);

        return tableLayout;
    }
	
    private void updateTableContent() {
        List<Event> events = getService().leseAlleEvents();
        getGrid().setItems(events);
    }
}
