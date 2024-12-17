package de.apicall.application.events.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EvolutionChainProcessor {

	
	private final RestTemplate restTemplate; 
	
	private final PokemonGotNextEvo gotNextEvo; 
	
	public EvolutionChainProcessor(RestTemplate restTemplate, PokemonGotNextEvo gotNextEvo) {
		this.restTemplate = restTemplate;
		this.gotNextEvo = gotNextEvo; 
	}
	
	
		
	
}
