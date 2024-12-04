package de.apicall.application.channel;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotJoinHandler extends ListenerAdapter {
	               			
	@Override 
	public void onGuildJoin(GuildJoinEvent event) {
		Guild guild = event.getGuild();
		
		boolean channelExists = guild.getTextChannelsByName("pokemon-channel", true).size() > 0;
		if (!channelExists) {
			
		guild.createTextChannel("pokemon-channel")
			.setTopic("Kanal wurde automatisch erstellt")
			.queue(channel -> {channel.sendMessage("Pokemon-theme channel created").queue();
		}, error -> {
			System.err.println(error.getMessage());
		}); 
		}		
	}
}
