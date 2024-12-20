package de.apicall.application.events.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PokemonGotNextEvo {

	private final GetRndmPokemon rndmPoke; 
	private final RestTemplate restTemplate;
	
	@Autowired
	public PokemonGotNextEvo(GetRndmPokemon rndmPoke, RestTemplate restTemplate) {
		this.rndmPoke = rndmPoke; 
		this.restTemplate = restTemplate; 		
	}
	
	public String returnEvo() {
		String pokemonName; 
		do {
			pokemonName = rndmPoke.getRndmPoke(); 
		}
		while (!checkIfFirstEvo(pokemonName)); 
		 
		
		return pokemonName;
	}
			
	public boolean checkIfFirstEvo(String pokemonName) {
		 if (pokemonName == null) {
		        throw new IllegalArgumentException("Pokémon-Name darf nicht null sein.");
		 }
		
	if(pokemonName != null) {
		String pokemonUrl = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonName;
		try {
		ResponseEntity<String> response = restTemplate.getForEntity(pokemonUrl, String.class);
		
	    if (response.getStatusCode().is2xxSuccessful()) {
	        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
	        String evolutionChainUrl = jsonNode.get("evolution_chain").get("url").asText();
	       
	        ResponseEntity<String> evolutionResponse = restTemplate.getForEntity(evolutionChainUrl, String.class);
	        if (evolutionResponse.getStatusCode().is2xxSuccessful()) {
	            JsonNode evolutionChain = new ObjectMapper().readTree(evolutionResponse.getBody());
	            String rootName = evolutionChain.get("chain").get("species").get("name").asText();
	            return rootName.equalsIgnoreCase(pokemonName); 
	        	}
	    	}
		} catch(Exception e) {
			System.err.println("Fehler beim Abrufen der Evolutionskette für " + pokemonName);
			 e.printStackTrace();
		        throw new IllegalArgumentException("Fehler beim Abrufen der Evolutionskette für " + pokemonName, e);
		    }
	}
	return false; 	 
	}
}
