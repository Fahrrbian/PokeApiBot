package de.apicall.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.apicall.application.events.service.PokemonGotNextEvo;


@RestController
@RequestMapping
public class EvolutionServiceController {

	private final PokemonGotNextEvo pokemonGotNextEvo;  
	
	@Autowired
	public EvolutionServiceController(PokemonGotNextEvo pokemonGotNextEvo) {
		this.pokemonGotNextEvo = pokemonGotNextEvo; 
	}
	@GetMapping("/random-first-stage-pokemon") 
	public String getRandomFirstStagePokemon() {
		return pokemonGotNextEvo.returnEvo(); 
	}
}
