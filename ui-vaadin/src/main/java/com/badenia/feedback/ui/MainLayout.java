package com.badenia.feedback.ui;

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
@StyleSheet("styles/format.css")
public class MainLayout extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public MainLayout() {
		add(createEmptySection(25));
		add(createQuestionSection(25));
		add(createQuestionOptionsSection(25));
		add(createEmptySection(25));
	}
	
	private HorizontalLayout createQuestionSection(int height) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setHeight(height+"%");
		layout.setWidth("100%");

		H2 lblTitle = new H2();
		lblTitle.setText("Dies ist ein Test");
		lblTitle.setClassName("titleLabel");
		layout.add(lblTitle);
		
		layout.setAlignItems(Alignment.CENTER);
		layout.addClassName("titleLabel");

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
			image.setSrc("frontend/images/0"+(i+1)+".png");

			Button imageButton = new Button();
			imageButton.setIcon(image);
			imageButton.setSizeUndefined();
			imageButton.addClickListener(event -> Notification.show("click!!!"));
			imageButton.setWidth(breite+"%");
			imageButton.setHeight("100%");
			imageButton.setClassName("imageButton");
			layout.add(imageButton);

			image.setWidth("100%");
		}
		
		return layout;
	}
}
