package de.apicall.application.events;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import de.apicall.application.controller.ApiController;
import de.apicall.application.events.pokemonName.PokemonNameProvider;
import de.apicall.application.events.pokemonName.PokemonNameProviderFactory;
import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
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
							//channel.sendFile(apicontroller.getData(query).getBody(), "pokemon.png").queue();
							BufferedImage image = ImageIO.read(new ByteArrayInputStream(apicontroller.getData(query).getBody()));
							BufferedImage resizedImage = resizeImage(image, 2560, 2560);
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							ImageIO.write(resizedImage, "png", baos);
							byte[] imageBytes = baos.toByteArray();
							
							channel.sendFile(imageBytes, "pokemon.png").queue();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                    }, error -> {
                        System.err.println("Fehler beim Erstellen des Kanals in " + guild.getName() + ": " + error.getMessage());
                    });
        }
    }
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, width, height, null);
        graphics.dispose();
        return resizedImage;
    }
    @Override 
    public void onMessageReceived(MessageReceivedEvent event) {
    	if(event.getChannel().getName().equalsIgnoreCase("pokemon-channel") && !event.getAuthor().isBot()) {
    		String content = event.getMessage().getContentRaw(); 
    		event.getMessage().delete().queue();
    	}
    }
}
