package de.apicall.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class DataListener extends ListenerAdapter {
	
	@Autowired
	private H2DataService dataService; 
	
	 @Autowired
	    public void setH2DataService(H2DataService dataService) {
	        this.dataService = dataService; // Setter-Injektion
	    }
	
	@Override 
	public void onReady(@NotNull ReadyEvent event) {
		dataService.initializeData();
	}
	
}
