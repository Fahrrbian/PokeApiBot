package de.apicall.commands;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.apicall.controller.ApiController;
import de.apicall.services.MessageService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ApiCommand extends ListenerAdapter implements BotCommand {

	private final RestTemplate restTemplate;
	private final MessageService messageService; 
	private final ApiController apicontroller; 
	
    public ApiCommand(RestTemplate restTemplate, MessageService messageService, ApiController apicontroller) {
        this.restTemplate = restTemplate;    
        this.messageService = messageService;
		this.apicontroller = apicontroller; 
    }

    
    	
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();      
        try {             
    	if (message.startsWith("!fetchdata")) {
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
        }catch(Exception e) {
    		System.err.print("Fehlerrr");
    		event.getChannel().sendMessage("Dieses Pokemon gibt es nicht!").queue(); 
    	
       }
       
	}



	@Override
	public void register(JDA jda) {
		// TODO Auto-generated method stub
		jda.addEventListener(this);
	}
}
