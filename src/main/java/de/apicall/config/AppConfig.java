package de.apicall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import de.apicall.commands.ApiCommand;
import de.apicall.controller.ApiController;
import de.apicall.services.MessageService;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApiCommand apiCommand(RestTemplate restTemplate, MessageService messageService, ApiController apiController) {
        return new ApiCommand(restTemplate, messageService, apiController);
    }
}
