package de.apicall.application.events.pokemonName;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RequestQueryPokemonNameProvider implements PokemonNameProvider {

	private final HttpServletRequest request; 
	
	public RequestQueryPokemonNameProvider(HttpServletRequest request) {
		this.request = request; 
	}

	@Override
	public String getPokemonName() {
		// TODO Auto-generated method stub
		 String pokemonName = "pikachu"; //pokemonDatabase.getRandomPokemonName().orElse(null);

	        // Optional: Zusätzliche Validierung oder Fallback
	        if (pokemonName == null || pokemonName.isEmpty()) {
	            throw new IllegalArgumentException("Kein Pokémon-Name gefunden oder gültig.");
	        }

	        return pokemonName;
	   
	}
	
}
