package de.apicall.application.services;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

	private String commandArgument; 
	
	public String getCommandArgument() {
		return commandArgument; 
	}
	public void setCommandArgument(String commandArgument) {
		this.commandArgument = commandArgument; //wird sinngemäß übergeben
	}
}
