package de.apicall.application.events;

import java.util.List;

import de.apicall.application.controller.ApiController;
import de.apicall.application.pokemonName.PokemonNameProvider;
import de.apicall.application.pokemonName.PokemonNameProviderFactory;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
	
	private ApiController apicontroller; 
	private PokemonNameProvider pokemonNameProvider; 
	private PokemonNameProviderFactory pokemonNameProviderFactory; 
	
	public void setApiController(ApiController apiController) {
		this.apicontroller = apiController; 
	}
	public void setPokemonNameProviderFactory(PokemonNameProviderFactory pokemonNameProviderFactory) {
		this.pokemonNameProviderFactory= pokemonNameProviderFactory; 
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
