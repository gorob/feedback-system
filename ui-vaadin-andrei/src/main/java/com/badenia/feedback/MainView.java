package com.badenia.feedback;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.events.ClickEvent;
import com.vaadin.flow.component.charts.events.MouseEventDetails;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import net.bytebuddy.agent.builder.AgentBuilder.Listener;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PageTitle("Feedbacksystem")
@StyleSheet("format/format.css")
public class MainView extends VerticalLayout {

    public MainView() {
    	
    	H1 title = new H1("Feedbacksystem");
    	title.setClassName("center");
    	add(title);
    	
        for (int i = 0; i < 3; i++) {
			
        	Button button = new Button("Event" + i,
        			event -> Notification.show("Clicked!"));
        	button.setSizeFull();
        	add(button);
		}
        
//        Image img1 = new Image();
//        img1.setSrc("frontend/images/smileyGruen.png");
//        img1.setClassName("imageCenter");
//        add(img1);
        
        Button b1 = new Button();
        b1.setClassName("buttonImage");
        b1.setSizeUndefined();
        add(b1);
        
        Button b2 = new Button("", e -> Notification.show("click!!!"));
        b2.setClassName("buttonImage");
        add(b2);
        
        
        
    }
    
}
