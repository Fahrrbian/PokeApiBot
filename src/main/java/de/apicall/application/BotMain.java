package de.apicall.application;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import de.apicall.handlers.CommandHandler;
import de.apicall.handlers.EventHandler;

@SpringBootApplication
public class BotMain implements CommandLineRunner {
	
    public static void main(String[] args) {
    	SpringApplication.run(BotMain.class, args); 
    }
        @Bean 
        public RestTemplate restTemplate() {
        	return new RestTemplate(); 
        }
     
    

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	     String token = System.getenv("DISCORD_TOKEN");
	     
	     if (token == null || token.isEmpty()) {
	            throw new IllegalArgumentException("Der Discord-Bot-Token ist nicht gesetzt. Bitte setzen Sie die Umgebungsvariable 'DISCORD_TOKEN'.");
	        }


	        JDA jda = JDABuilder.createDefault(token)
	                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
	                .setActivity(Activity.playing("API-READY"))
	                .build();

	        RestTemplate restTemplate = restTemplate(); 
	        new CommandHandler(jda, restTemplate);
	        new EventHandler(jda);
	}
}
