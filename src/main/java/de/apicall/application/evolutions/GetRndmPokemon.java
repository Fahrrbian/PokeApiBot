package de.apicall.application.evolutions;

import java.util.Random;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class GetRndmPokemon {

	private final RestTemplate restTemplate; 
	
	public GetRndmPokemon(RestTemplate restTemplate) {
		this.restTemplate = restTemplate; 
	}
	
	public String getRndmPoke() {
	int maxPokemon = 1010; //MAX Number of Pokemon in the api 
	int randomId = new Random().nextInt(maxPokemon) +1; 
	String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/" + randomId;
	try {
	ResponseEntity<String> response = restTemplate.getForEntity(pokemonUrl, String.class); 
	
	if(response.getStatusCode().is2xxSuccessful()) {
		JsonNode jsonNode = new ObjectMapper().readTree(response.getBody()); 
		return jsonNode.get("name").asText(); 
		}
	} catch(Exception e) {
		throw new IllegalArgumentException("Fehler beim Abruf eines zufälligen Pokemons" , e); 
	}
	throw new IllegalArgumentException("Fehler beim Abruf");
	}
}
