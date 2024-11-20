package de.apicall.handlers;

import de.apicall.data.DataListener;
import de.apicall.events.ReadyListener;

import de.apicall.roles.listeners.RoleListener;
import de.apicall.roles.services.RoleService;
import net.dv8tion.jda.api.JDA;

public class EventHandler {
	
    public EventHandler(JDA jda) {
        jda.addEventListener(new ReadyListener());    
		// Weitere Events hinzufügen
        jda.addEventListener(new RoleListener()); 
        jda.addEventListener(new DataListener());
    }

}
