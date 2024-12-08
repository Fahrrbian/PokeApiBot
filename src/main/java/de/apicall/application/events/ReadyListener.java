package de.apicall.application.events;

import java.util.List;

import org.springframework.stereotype.Component;

import de.apicall.application.controller.ApiController;
import de.apicall.application.events.pokemonName.PokemonNameProvider;
import de.apicall.application.events.pokemonName.PokemonNameProviderFactory;
import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class ReadyListener extends ListenerAdapter {
	
	private ApiController apicontroller; 
	private PokemonNameProvider pokemonNameProvider; 
	private PokemonNameProviderFactory pokemonNameProviderFactory; 
	
	public ReadyListener(ApiController apiController, PokemonNameProviderFactory pokemonNameProviderFactory) {
		this.apicontroller = apiController; 
		this.pokemonNameProviderFactory = pokemonNameProviderFactory;
		 
	}
	

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Bot ist eingeloggt als " + event.getJDA().getSelfUser().getAsTag());
        
        this.pokemonNameProvider = pokemonNameProviderFactory.getProvider("query"); 
        
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
                        try {
                        	String query = pokemonNameProvider.getPokemonName();
                        	System.out.println("QUERY PARAMTER///////////" + query);
							channel.sendFile(apicontroller.getData(query).getBody(), "pokemon.png").queue();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                    }, error -> {
                        System.err.println("Fehler beim Erstellen des Kanals in " + guild.getName() + ": " + error.getMessage());
                    });
        }
    }
}
