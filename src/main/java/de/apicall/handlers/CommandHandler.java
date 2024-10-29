package de.apicall.handlers;

import org.springframework.web.client.RestTemplate;

import de.apicall.commands.ApiCommand;
import net.dv8tion.jda.api.JDA;

public class CommandHandler {
    public CommandHandler(JDA jda, RestTemplate restTemplate) {
        jda.addEventListener(new ApiCommand(restTemplate));
        // Weitere Befehle hinzufügen
    }
}
