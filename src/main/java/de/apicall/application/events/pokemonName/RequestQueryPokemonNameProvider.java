package de.apicall.application.events.pokemonName;

import org.springframework.stereotype.Component;

import de.apicall.application.entity.FirstEvoPokemon;
import de.apicall.application.entity.Pokemon;
import de.apicall.application.events.service.FirstEvoPokemonService;
import de.apicall.application.repository.FristEvoPokemonRepository;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class RequestQueryPokemonNameProvider implements PokemonNameProvider {

	private final FristEvoPokemonRepository repo;  
	
	public RequestQueryPokemonNameProvider(FristEvoPokemonRepository repo) {
		this.repo = repo; 
	}

	@Override
	public String getPokemonName() {
		
		return repo.findFirstByOrderByIdDesc()
		.getName();
		
		/*
		return repo.findFirstByOrderByIdDesc()
				.map(FirstEvoPokemon::getName)
				.orElseThrow(() -> new IllegalArgumentException("Kein Pokemon gefunden"));
		
		*/
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
