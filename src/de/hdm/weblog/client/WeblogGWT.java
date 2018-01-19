package de.hdm.weblog.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.weblog.shared.BlogAdministration;
import de.hdm.weblog.shared.BlogAdministrationAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WeblogGWT implements EntryPoint {
	/**
	 * Create a remote service proxy to talk to the server-side WebLog service.
	 */
	private final BlogAdministrationAsync weblog = GWT.create(BlogAdministration.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		VerticalPanel vp = new VerticalPanel();
		RootPanel.get().add(vp);
		vp.add(new Label("nur ein Test"));
	}


}
