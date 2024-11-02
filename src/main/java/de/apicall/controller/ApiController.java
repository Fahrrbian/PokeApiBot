package de.apicall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.apicall.commands.ApiCommand;
import de.apicall.services.MessageService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.springframework.http.HttpHeaders;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

@RestController
@RequestMapping("/api")
public class ApiController {

private final RestTemplate restTemplate;
private final MessageService messageService; 


@Autowired
public ApiController(RestTemplateBuilder builder, MessageService messageService) {
    this.restTemplate = builder.build();
    this.messageService = messageService; 
}



@GetMapping("/pokemon") 
public ResponseEntity<byte[]> getData() throws Exception{
	String pokemonName = messageService.getCommandArgument(); 
	  if (pokemonName == null || pokemonName.isEmpty()) {
          //return ResponseEntity.badRequest().body("No valid Pokémon name provided.");
      }
	String apiUrl ="https://pokeapi.co/api/v2/pokemon/" + pokemonName; 
	String response = restTemplate.getForObject(apiUrl, String.class);
	
	 JSONObject jsonResponse = new JSONObject(response);
     String imageUrl = jsonResponse
                         .getJSONObject("sprites")
                         .getString("front_default");

     URL url = new URL(imageUrl); 
     HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
     connection.setRequestMethod("GET"); 
     connection.connect(); 
     
     InputStream inputStream = connection.getInputStream(); 
     byte[] imageBytes = inputStream.readAllBytes(); 
     
     HttpHeaders headers = new HttpHeaders(); 
     headers.setContentType(MediaType.IMAGE_PNG); 
     
     return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	
	}
}

