package de.apicall.application.events.pokemonName;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import de.apicall.application.services.MessageService;

@Primary
@Component("messageServicePokemonNameProvider")
public class MessageServicePokemonNameProvider implements PokemonNameProvider {
	
	private final MessageService messageService; 
	
	public MessageServicePokemonNameProvider(MessageService messageService) {
		this.messageService = messageService; 
	}
	@Override
	public String getPokemonName() {
		return messageService.getCommandArgument(); 
	}
	
}
