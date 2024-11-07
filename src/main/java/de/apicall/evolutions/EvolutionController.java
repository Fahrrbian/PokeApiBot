package de.apicall.evolutions;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.apicall.commands.ApiCommand;
import de.apicall.controller.ApiController;
import de.apicall.services.MessageService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

@RestController
@RequestMapping("/api")
public class EvolutionController {

	private final RestTemplate restTemplate;
	private final MessageService messageService;
	private final ApiController apiController; 

	@Autowired
	public EvolutionController(RestTemplateBuilder builder, MessageService messageService, ApiController apiController) {
	    this.restTemplate = builder.build();
	    this.messageService = messageService;
		this.apiController = apiController; 
	}
	
	@GetMapping("/evolutionRequest") 
	public List<MessageEmbed> getPokemonEvolution() throws Exception {
		List<MessageEmbed> embeds = new ArrayList<>();
		String pokemonName = messageService.getCommandArgument(); 
		 if (pokemonName == null || pokemonName.isEmpty()) {
	          //return ResponseEntity.badRequest().body("No valid Pokémon name provided.");
	      }
		 String pokemonUrl = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonName;	
		 String speciesResponse = restTemplate.getForObject(pokemonUrl, String.class);
		 JSONObject speciesJson = new JSONObject(speciesResponse);
		 
		 String evolutionChainUrl = speciesJson.getJSONObject("evolution_chain").getString("url");
		 String response = restTemplate.getForObject(evolutionChainUrl, String.class); 		 		 	
		 JSONObject jsonResponse = new JSONObject(response);
		 JSONObject chain = jsonResponse.getJSONObject("chain");
		 /*
		  EmbedBuilder embed = new EmbedBuilder();
		  embed.setTitle("Evolutionskette von " + pokemonName);
		  embed.setColor(Color.BLUE);		 		
		 */
		 Map<String, Object> responseMap = new HashMap<>();
	     Map<String, byte[]> imagesMap = new HashMap<>();
		  
	     while (chain != null) {
	            String speciesName = chain.getJSONObject("species").getString("name");

	            // Abruf des Bildes für das aktuelle Pokémon
	            byte[] imageBytes = apiController.getPokemonImage(speciesName);
	            String imageUrl = "attachment://" + speciesName + ".png";

	            // Erstelle ein Embed für die aktuelle Evolutionsstufe
	            EmbedBuilder embed = new EmbedBuilder();
	            embed.setTitle("Evolution: " + speciesName);
	            embed.setDescription("Pokémon: " + speciesName);
	            embed.setColor(Color.BLUE);
	            embed.setImage(imageUrl);  // Füge das Bild zur Evolutionsstufe hinzu

	            embeds.add(embed.build());  // Füge den erstellten Embed der Liste hinzu

	            // Gehe zur nächsten Evolutionsstufe
	            JSONArray evolvesToArray = chain.getJSONArray("evolves_to");
	            if (evolvesToArray.isEmpty()) {
	                break;
	            }
	            chain = evolvesToArray.getJSONObject(0);
	        }

	        return embeds;  
		 /*				
		 String evo = evolutions.toString(); 
		 
		 if (evo.isEmpty()) {
		        return ResponseEntity.ok("Keine Evolutionen gefunden.");
		    } else {
		        return ResponseEntity.ok(evo);
		    }*/		 
	}
}
