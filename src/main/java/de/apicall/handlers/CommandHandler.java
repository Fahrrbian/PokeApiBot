package de.apicall.handlers;

import org.springframework.web.client.RestTemplate;


import de.apicall.utils.CommandRegistry;
import net.dv8tion.jda.api.JDA;

public class CommandHandler {
    public CommandHandler(JDA jda, CommandRegistry commandRegistry) {
        commandRegistry.registerAll(jda);
        // Weitere Befehle hinzufügen
    }
}
