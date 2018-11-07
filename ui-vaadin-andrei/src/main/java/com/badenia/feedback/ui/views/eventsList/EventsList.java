package com.badenia.feedback.ui.views.eventsList;

import com.badenia.feedback.ui.MainLayout;
import com.badenia.feedback.ui.views.reviewslist.ReviewsList.ReviewsModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Event List")
@Tag("reviews-list")
@HtmlImport("frontend://src/views/reviewslist/reviews-list.html")
public class EventsList extends PolymerTemplate<ReviewsModel> {

	
	public EventsList() {
		Button b = new Button();
	}
}
