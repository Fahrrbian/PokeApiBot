package de.apicall.application.events.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EvolutionChainProcessor {

	
	private final RestTemplate restTemplate; 
	
	private final PokemonGotNextEvo gotNextEvo; 
	
	public EvolutionChainProcessor(RestTemplate restTemplate, PokemonGotNextEvo gotNextEvo) {
		this.restTemplate = restTemplate;
		this.gotNextEvo = gotNextEvo; 
	}
	
	public String evolvePokemon(String currentPokemonName) {
		if(!gotNextEvo.checkIfFirstEvo(currentPokemonName)) {
			throw new IllegalArgumentException("Pokemon ist nicht in der ersten Evolutionsstufe"+ currentPokemonName); 
		}
		
		String evolutionChainUrl = getEvolutionChainUrl(currentPokemonName);
		if(evolutionChainUrl == null) {
			throw new IllegalArgumentException("Keine Evo-Kette für" + currentPokemonName); 
		}
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(evolutionChainUrl, String.class); 
			if(response.getStatusCode().is2xxSuccessful()) {
				JsonNode evolutionChain = new ObjectMapper().readTree(response.getBody());
				JsonNode currentNode = findCurrentNodeInEvolutionChain(evolutionChain, currentPokemonName);
				
				if(currentNode != null) {
					JsonNode nextEvolution = currentNode.get("evolves_to").get(0); 
					if(nextEvolution != null) {
						return nextEvolution.get("spieces").get("name").asText(); 
					}
 				}
			}
		} catch(Exception e) {
			throw new IllegalArgumentException("Fehler beim Abrufen der Evo für: " + currentPokemonName, e); 
		}
		return null; 
	}
	
	
	
    private String getEvolutionChainUrl(String pokemonName) {
        String pokemonUrl = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonName;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(pokemonUrl, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
                return jsonNode.get("evolution_chain").get("url").asText();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Abrufen der Evolutionskette-URL für: " + pokemonName, e);
        }
        return null;
    }
	private JsonNode findCurrentNodeInEvolutionChain(JsonNode evolutionChain, String pokemonName) {
		JsonNode currentNode = evolutionChain.get("chain"); 
		while(currentNode != null) {
			String currentName = currentNode.get("species").get("name").asText(); 
			if(currentName.equalsIgnoreCase(pokemonName)) {
				return currentNode; 
			}
			currentNode = currentNode.get("evolves_to").get(0); 
		}
		return null; 
	}
	
}
