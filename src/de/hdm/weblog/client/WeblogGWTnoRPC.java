package de.hdm.weblog.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WeblogGWTnoRPC implements EntryPoint {
	
	HTML headline = new HTML();
	int counter = 0;
	
	TextBox titel = new TextBox();
	TextBox untertitel = new TextBox();
	TextArea inhalt = new TextArea();
	Button speichern = new Button("Speichern");

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		updateCounter();
		
		VerticalPanel vp = new VerticalPanel();
		vp.add(headline);
		
		inhalt.setCharacterWidth(40);
		inhalt.setHeight("10em");
		Grid fields = new Grid(5,2);
		fields.setWidget(0, 0, new HTML("<b>Eintrag</b>"));
		fields.setWidget(1, 0, new Label("Titel:"));
		fields.setWidget(1, 1, titel);
		fields.setWidget(2, 0, new Label("Untertitel"));
		fields.setWidget(2, 1, untertitel);
		fields.setWidget(3, 0, new Label("Text"));
		fields.setWidget(3, 1, inhalt);
		fields.setWidget(4, 1, speichern);
		
		vp.add(fields);

		RootPanel.get().add(vp);
		
		speichern.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent ce) {
				Window.alert(titel.getText() + "\n" + untertitel.getText() + "\n" + inhalt.getText());
				
				updateCounter();			
			}
		});
	}
	
	private void updateCounter() {
		counter++;
		updateFields();	
	}
	
	
	private void updateFields() {
		titel.setText("");
		untertitel.setText("");
		inhalt.setText("");
		headline.setHTML("<h1>Blogeintrag Nr. " + counter + " anlegen</h1>");
	}

}
