package de.apicall.roles.listeners;

import net.dv8tion.jda.api.JDA;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import de.apicall.roles.services.RoleService;
import de.apicall.services.PokemonService;
import de.apicall.test.H2DatabaseTest;
import de.apicall.commands.BotCommand;

public class RoleCommandListener extends ListenerAdapter implements BotCommand {

	private RoleService roleService; 
	private PokemonService pokemonService;
	private H2DatabaseTest h2test; 
	
	public RoleCommandListener(RoleService roleService, PokemonService pokemonService, H2DatabaseTest h2test) {
		this.roleService = roleService; 
		this.pokemonService = pokemonService; 
		this.h2test = h2test; 
	}
	
	 @Override
	    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
	        String message = event.getMessage().getContentRaw();	       
	        if(message.startsWith("!trainer")) { 	      
	        String[] args = message.split(" ");  
	        if (args[0].equalsIgnoreCase("!trainer")) {
	            if (args.length < 2) {
	                event.getChannel().sendMessage("Bitte erwähne einen Benutzer, um die Trainer-Rolle zuzuweisen!").queue();
	                return;
	            }

	            // Extrahiere die Benutzer-ID aus der Nachricht
	            String userId = args[1].replaceAll("[^0-9]", "");  // Entferne Sonderzeichen (<@!1234567890>)
	            Member member = event.getGuild().getMemberById(userId);

	            if (member != null) {
	                roleService.assignTrainerRole(member, event);
	            } else {
	                event.getChannel().sendMessage("Benutzer konnte nicht gefunden werden.").queue();
	            }
	        }
	        }
	        else if(message.startsWith("!myPokemon")) {	        
	        	String[] args = message.split(" ");
	        	if(args[0].equalsIgnoreCase("!myPokemon")) {
	        		if(args.length<2) {
	        			event.getChannel().sendMessage("Nutzer bitte erwähnen!").queue(); 
	        			return; 
	        		}
	        		  String userId = args[1].replaceAll("[^0-9]", "");  // Entferne Sonderzeichen (<@!1234567890>)
	  	            Member member = event.getGuild().getMemberById(userId);

	  	            if (member != null) {
	  	            	List<String> object =  pokemonService.getPokemonsForUser(userId);  
	  	            	String pokemonString = String.join("\n", object);
	  	            	event.getChannel().sendMessage("Deine Liste: "+pokemonString).queue();
	  	            }
	        	}
	        }
	        else if(message.startsWith("!test")) {
	        	h2test.testSaveAndRetrieveUserPokemon();
	        }
	    }
	 
	  
		@Override
		public void register(JDA jda) {
			// TODO Auto-generated method stub
			jda.addEventListener(this);
			
		}
	}
