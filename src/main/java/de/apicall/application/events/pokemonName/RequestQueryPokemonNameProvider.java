package de.apicall.application.events.pokemonName;

import org.springframework.stereotype.Component;

import de.apicall.application.entity.Pokemon;
import de.apicall.application.repository.PokemonRepository;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class RequestQueryPokemonNameProvider implements PokemonNameProvider {

	private final PokemonRepository repo;  
	
	public RequestQueryPokemonNameProvider(PokemonRepository repo) {
		this.repo = repo; 
	}

	@Override
	public String getPokemonName() {
		
		return repo.findById(1L)
				.map(Pokemon::getName)
				.orElseThrow(() -> new IllegalArgumentException("Kein Pokemon gefunden"));
		
		
		// TODO Auto-generated method stub
		/* String pokemonName = "pikachu"; //pokemonDatabase.getRandomPokemonName().orElse(null);

	        // Optional: Zusätzliche Validierung oder Fallback
	        if (pokemonName == null || pokemonName.isEmpty()) {
	            throw new IllegalArgumentException("Kein Pokémon-Name gefunden oder gültig.");
	        }

	        return pokemonName;
	   */
	}
	
}
