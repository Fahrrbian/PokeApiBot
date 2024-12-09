package de.apicall.application.commands;

import java.util.List;
import java.util.Map;

import javax.management.relation.Role;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.apicall.application.controller.ApiController;
import de.apicall.application.events.pokemonName.PokemonNameProvider;
import de.apicall.application.events.pokemonName.PokemonNameProviderFactory;
import de.apicall.application.evolutions.EvolutionController;
import de.apicall.application.roles.config.RoleConfigLoader;
import de.apicall.application.roles.enums.CommandName;
import de.apicall.application.services.MessageService;
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
	private final RoleConfigLoader roleConfig;
	private PokemonNameProvider pokemonNameProvider; 
	private PokemonNameProviderFactory pokemonNameProviderFactory;
	
    public ApiCommand(RestTemplate restTemplate, MessageService messageService, 
    		ApiController apicontroller, EvolutionController evocontroller, 
    		RoleConfigLoader roleConfig, PokemonNameProvider pokemonNameProvider,
    		PokemonNameProviderFactory pokemonNameProviderFactory) {
        this.restTemplate = restTemplate;    
        this.messageService = messageService;
		this.apicontroller = apicontroller;
		this.evocontroller = evocontroller;
		this.roleConfig = roleConfig;
		this.pokemonNameProvider = pokemonNameProvider; 
		this.pokemonNameProviderFactory = pokemonNameProviderFactory;
    }

    
    	
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
    	this.pokemonNameProvider = pokemonNameProviderFactory.getProvider("message");
        String message = event.getMessage().getContentRaw();      
        try {             
    	if (message.startsWith(CommandName.POKEMON.getCommand())) {
        	String[] args = message.split(" ");         	        	        		    
        	 if (args.length > 1) {
                 messageService.setCommandArgument(args[1]);
                  
                 event.getChannel().sendFile(apicontroller.getData(pokemonNameProvider.getPokemonName()).getBody(), "pokemon.png").queue();
             }
    	}
    	else if(message.startsWith("!commands")) {
    		
    	    String allCommands = String.join("\n", CommandName.getAllCommandsWithDescriptions());
    	    event.getChannel().sendMessage("Hier sind die verfügbaren Commands:\n" + allCommands).queue();

    		 
    	}
    	else if(message.startsWith(CommandName.INFO.getCommand())) {
     	   String[] args = message.split(" ");
     	   if (args.length > 1) {
     		   messageService.setCommandArgument(args[1]);
     		   String info = apicontroller.getPokemonInfo().getBody(); 
     		   event.getChannel().sendMessage(info).queue(); 
     	   }
         }
    	 else if(message.startsWith(CommandName.ABILITIES.getCommand())) {
        	   String[] args = message.split(" ");
        	   if (args.length > 1) {
        		   messageService.setCommandArgument(args[1]);
        		   String abilities = apicontroller.getPokemonAbilities().getBody(); 
        		   event.getChannel().sendMessage(args[1] + " hat die Fähigkeiten " + abilities).queue(); 
        	   }
            } 
    	   else if (message.startsWith(CommandName.PERMISSION.getCommand())) {    		    	    		 
		        String[] args = message.split(" ");
   		    if (args.length > 1) {
   		        messageService.setCommandArgument(args[1]);
		    	String roleName = args[1]; 
   		        List<String> permissions = roleConfig.getPermissionsForRole(roleName);
   		        if (permissions.isEmpty()) {
   		            event.getChannel().sendMessage("Keine Berechtigungen für die Rolle " + roleName + " gefunden.").queue();
   		        } else {
   		            String permissionList = String.join(", ", permissions);
   		            event.getChannel().sendMessage("Die Berechtigungen für die Rolle " + roleName + " sind: " + permissionList).queue();
   		        }
   		 } else {
   	        event.getChannel().sendMessage("Bitte gib eine Rolle an, z. B.: `!permission Trainer`").queue();
   	    
		    }
    	 }    	
    	 else if (message.startsWith(CommandName.EVOLVE.getCommand())) {
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
