package de.apicall.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import de.apicall.commands.BotCommand;
import net.dv8tion.jda.api.JDA;

@Component
public class CommandRegistry {

	private final List<BotCommand> commands; 
	
	public CommandRegistry(List<BotCommand> commands) {
		this.commands = commands; 
	}
	
	public void registerAll(JDA jda) {
		for(BotCommand command : commands) {
			command.register(jda);
		}
	}
}
