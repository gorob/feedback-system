package com.badenia.feedback;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PageTitle("Feedbacksystem")
@StyleSheet("format/format.css")
public class MainView extends VerticalLayout {

	public MainView() {

		//    	H1 title = new H1("Feedbacksystem");
		//    	title.setClassName("center");
		//    	add(title);


		setHeight("100%");	

		add(createQuestionSection(33));
		add(createQuestionOptionsSection(33));
		add(createEmptySection(33));
	}
	
	private HorizontalLayout createQuestionSection(int height) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setHeight(height+"%");
		layout.setWidth("100%");

		H2 lblTitle = new H2();
		lblTitle.setText("Wie hat Ihnen das Essen heute geschmeckt?");
		lblTitle.setClassName("titelLabel");
		layout.add(lblTitle);
		
		layout.setAlignItems(Alignment.CENTER);

		return layout;
	}
	
	private HorizontalLayout createEmptySection(int height) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setHeight(height+"%");
		layout.setWidth("100%");
		return layout;
	}
	
	
	private HorizontalLayout createQuestionOptionsSection(int height) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setHeight(height+"%");
		layout.setWidth("100%");

		int breite = 100/3;

		for (int i = 0; i < 3; i++) {
			Image image = new Image();
			image.setSrc("frontend/images/smileyGelb.png");

			Button imageButton = new Button();
			imageButton.setIcon(image);
			imageButton.setSizeUndefined();
			imageButton.addClickListener(event -> Notification.show("click!!!"));
			imageButton.setWidth(breite+"%");
			imageButton.setHeight("100%");
			imageButton.setClassName("imageButton");
			layout.add(imageButton);

			image.setWidth("80%");
		}
		
		return layout;
	}
}
