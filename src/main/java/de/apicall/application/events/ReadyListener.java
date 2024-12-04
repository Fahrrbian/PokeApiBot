package de.apicall.application.events;

import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Bot ist eingeloggt als " + event.getJDA().getSelfUser().getAsTag());
        
        List<Guild> guilds = event.getJDA().getGuilds(); 
        for(Guild guild : guilds) {
        	createPokemonChannelIfNotExists(guild);
        }
     }

    private void createPokemonChannelIfNotExists(Guild guild) {        
        if (guild.getTextChannelsByName("pokemon-channel", true).isEmpty()) {
            guild.createTextChannel("pokemon-channel")
                    .setTopic("Dieser Kanal wurde automatisch erstellt.")
                    .queue(channel -> {
                        channel.sendMessage("pokeon-theme channel").queue();
                    }, error -> {
                        System.err.println("Fehler beim Erstellen des Kanals in " + guild.getName() + ": " + error.getMessage());
                    });
        }
    }
}
