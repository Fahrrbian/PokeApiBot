package de.apicall.handlers;

import org.springframework.web.client.RestTemplate;

import de.apicall.commands.ApiCommand;
import de.apicall.controller.ApiController;
import de.apicall.services.MessageService;
import net.dv8tion.jda.api.JDA;

public class CommandHandler {
    public CommandHandler(JDA jda, RestTemplate restTemplate, MessageService messageService, ApiController apiController) {
        jda.addEventListener(new ApiCommand(restTemplate, messageService, apiController));
        // Weitere Befehle hinzufügen
    }
}
