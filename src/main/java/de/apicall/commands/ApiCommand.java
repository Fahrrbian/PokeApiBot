package de.apicall.commands;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.apicall.controller.ApiController;
import de.apicall.evolutions.EvolutionController;
import de.apicall.services.MessageService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

public class ApiCommand extends ListenerAdapter implements BotCommand {

	private final RestTemplate restTemplate;
	private final MessageService messageService; 
	private final ApiController apicontroller; 
	private final EvolutionController evocontroller; 
	
    public ApiCommand(RestTemplate restTemplate, MessageService messageService, ApiController apicontroller, EvolutionController evocontroller) {
        this.restTemplate = restTemplate;    
        this.messageService = messageService;
		this.apicontroller = apicontroller;
		this.evocontroller = evocontroller; 
    }

    
    	
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();      
        try {             
    	if (message.startsWith("!pokemon")) {
        	String[] args = message.split(" ");         	        	        		    
        	 if (args.length > 1) {
                 messageService.setCommandArgument(args[1]);
                  
                 event.getChannel().sendFile(apicontroller.getData().getBody(), "pokemon.png").queue();
             }
    	}
    	else if(message.startsWith("!info")) {
     	   String[] args = message.split(" ");
     	   if (args.length > 1) {
     		   messageService.setCommandArgument(args[1]);
     		   String info = apicontroller.getPokemonInfo().getBody(); 
     		   event.getChannel().sendMessage(info).queue(); 
     	   }
         }
    	 else if(message.startsWith("!abilities")) {
        	   String[] args = message.split(" ");
        	   if (args.length > 1) {
        		   messageService.setCommandArgument(args[1]);
        		   String abilities = apicontroller.getPokemonAbilities().getBody(); 
        		   event.getChannel().sendMessage(args[1] + " hat die Fähigkeiten " + abilities).queue(); 
        	   }
            } 
    	 else if (message.startsWith("!evolution")) {
    		    String[] args = message.split(" ");
    		    if (args.length > 1) {
    		        messageService.setCommandArgument(args[1]);
    		        try {    		    
    		            // Erhalte die Liste der Embeds von evocontroller.getPokemonEvolution()
    		            List<MessageEmbed> embeds = evocontroller.getPokemonEvolution();

    		            // Baue die Nachricht mit den Embeds und Bildern auf
    		            for (MessageEmbed embed : embeds) {
    		                String speciesName = embed.getTitle().replace("Evolution: ", "");
    		                byte[] imageBytes = apicontroller.getPokemonImage(speciesName);
    		                String imageName = speciesName + ".png";

    		                // Sende jeden Embed einzeln mit dem zugehörigen Bild
    		                event.getChannel()
    		                    .sendMessageEmbeds(embed)
    		                    .addFile(imageBytes, imageName)
    		                    .queue();
    		            }
    		        } catch (Exception e) {
    		            e.printStackTrace();
    		            System.err.print("Fehler");
    		            event.getChannel().sendMessage("Dieses Pokémon gibt es nicht!").queue(); 
    		        }
    		    }
    	 }
    		    } catch(Exception e) {
    		    	e.printStackTrace();
    		    	System.err.print(e.getMessage());
    		    }
    		}
        
    

	@Override
	public void register(JDA jda) {
		// TODO Auto-generated method stub
		jda.addEventListener(this);
	}
}
