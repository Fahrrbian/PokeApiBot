package de.apicall.application.evolutions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvolutionServiceController {

	private final PokemonGotNextEvo pokemonGotNextEvo;  
	
	public EvolutionServiceController(PokemonGotNextEvo pokemonGotNextEvo) {
		this.pokemonGotNextEvo = pokemonGotNextEvo; 
	}
	@GetMapping("/random-first-stage-pokemon") 
	public String getRandomFirstStagePokemon() {
		return pokemonGotNextEvo.returnEvo(); 
	}
		
	
	
	
}
