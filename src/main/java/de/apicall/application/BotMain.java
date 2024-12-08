package de.apicall.application; 

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.management.relation.RoleList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ch.qos.logback.core.Context;
import de.apicall.application.events.ReadyListener;
import de.apicall.application.handlers.CommandHandler;
import de.apicall.application.handlers.EventHandler;
import de.apicall.application.roles.listeners.RoleListener;
import de.apicall.application.utils.CommandRegistry;


@SpringBootApplication//(scanBasePackages = "de.apicall")
public class BotMain implements CommandLineRunner {
	
    private static final String DISCORD_TOKEN_ENV = "DISCORD_TOKEN";   

    @Autowired 
    private CommandRegistry commandRegistry;

	private ApplicationContext applicationC; 
    
    public BotMain(ApplicationContext applicationC) {
    	this.applicationC = applicationC; 
    }
    
    
    public static void main(String[] args) {
    	//SpringApplication.run(BotMain.class, args);
    	SpringApplication.run(BotMain.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub		 
		
	     String token = getDiscordToken(); 	     	     

	        JDA jda = JDABuilder.createDefault(token)
	                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
	                .setActivity(Activity.playing("API-READY"))	   
	                .build();
	        
	        jda.addEventListener(
	    		  applicationC.getBean(ReadyListener.class),
	    		  applicationC.getBean(RoleListener.class)
	    		  );
	      
	      //jda.addEventListener(applicationContext.getBean(DataListener.class));	      	 
	      new CommandHandler(jda, commandRegistry); 	      	     	       
	      //new EventHandler(jda); 
	      
	 
	        

	}
	
	   private String getDiscordToken() {
	        String token = System.getenv(DISCORD_TOKEN_ENV);
	        if (token == null || token.isEmpty()) {
	            throw new IllegalArgumentException("Der Discord-Bot-Token ist nicht gesetzt. Bitte setzen Sie die Umgebungsvariable '" + DISCORD_TOKEN_ENV + "'.");
	        }
	        return token;
	    }
}
