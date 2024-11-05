package de.apicall.evolutions;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.apicall.services.MessageService;

@RestController
@RequestMapping("/api")
public class EvolutionController {

	private final RestTemplate restTemplate;
	private final MessageService messageService; 

	@Autowired
	public EvolutionController(RestTemplateBuilder builder, MessageService messageService) {
	    this.restTemplate = builder.build();
	    this.messageService = messageService; 
	}
	
	@GetMapping("/evolutionRequest") 
	public ResponseEntity<String> getPokemonEvolution() throws Exception {
		StringBuilder evolutions = new StringBuilder();
		String pokemonName = messageService.getCommandArgument(); 
		int id = 8; 
		 if (pokemonName == null || pokemonName.isEmpty()) {
	          //return ResponseEntity.badRequest().body("No valid Pokémon name provided.");
	      }
		 String apiUrl = "https://pokeapi.co/api/v2/evolution-chain/" + id;
		 String response = restTemplate.getForObject(apiUrl, String.class); 
		 
		 JSONObject jsonResponse = new JSONObject(response);
		 JSONObject chain = jsonResponse.getJSONObject("chain");
		 

		 
		 while (chain != null) {
		     String speciesName = chain.getJSONObject("species").getString("name");
		     if (evolutions.length() > 0) {
		         evolutions.append(" -> "); 
		     }
		     evolutions.append(speciesName);

		     JSONArray evolvesToArray = chain.getJSONArray("evolves_to");
		     if (evolvesToArray.isEmpty()) {
		         break;
		     }
		     chain = evolvesToArray.getJSONObject(0); 
		 }
		 				
		 String evo = evolutions.toString(); 
		 
		 if (evo.isEmpty()) {
		        return ResponseEntity.ok("Keine Evolutionen gefunden.");
		    } else {
		        return ResponseEntity.ok(evo);
		    }		 
	}
}
