package de.hdm.weblog.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.weblog.shared.BlogAdministration;
import de.hdm.weblog.shared.BlogAdministrationAsync;
import de.hdm.weblog.shared.Blogeintrag;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WeblogGWT implements EntryPoint {
	/**
	 * Create a remote service proxy to talk to the server-side WebLog service.
	 */
	private final BlogAdministrationAsync weblog = GWT.create(BlogAdministration.class);
	HTML headline = new HTML("<h1>Blogeintrag Nr. 0 anlegen</h1>");
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
				
				addBlogeintrag();				
			}
		});
	}
	
	private void updateCounter() {
		weblog.findAll(new AsyncCallback<Vector<Blogeintrag>>() {
			public void onFailure(Throwable error) {
				Window.alert("Something went wrong when fetching blog entries.");
			}
			
			public void onSuccess(Vector<Blogeintrag> entries) {
				if (entries!=null) {
					counter = entries.size() + 1;
				}
				updateFields();
			}
		});
	}
	
	private void updateFields() {
		titel.setText("");
		untertitel.setText("");
		inhalt.setText("");
		headline.setHTML("<h1>Blogeintrag Nr. " + counter + " anlegen</h1>");
	}
	
	private void addBlogeintrag() {
		weblog.createBlogeintrag(inhalt.getText(), titel.getText(), untertitel.getText(), 
				new AsyncCallback<Blogeintrag>() {
			public void onFailure(Throwable error) {
				Window.alert("Something went wrong when adding the blog entry.");
			}
			
			public void onSuccess(Blogeintrag arg) {
				if (arg != null) {
					updateCounter();
				}
			}
		});
	}


}
