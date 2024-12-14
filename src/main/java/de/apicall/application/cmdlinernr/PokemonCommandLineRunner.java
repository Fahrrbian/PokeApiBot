package de.apicall.application.cmdlinernr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import de.apicall.application.entity.Pokemon;
import de.apicall.application.events.service.FirstEvoPokemonService;
import de.apicall.application.repository.PokemonRepository;


//@Order(2)
@Component
public class PokemonCommandLineRunner implements CommandLineRunner{

	@Autowired
	private final PokemonRepository pokemonRepository; 
	
	@Autowired
	private final FirstEvoPokemonService evoPokemonService; 
	
	public PokemonCommandLineRunner(PokemonRepository pokemonRepository, FirstEvoPokemonService evoPokemonService) {
		this.pokemonRepository = pokemonRepository; 
		this.evoPokemonService = evoPokemonService; 
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PokemonRepository bean injected: " + (pokemonRepository != null));
		
		 Pokemon pidgey = new Pokemon();
		 	String pokeName = "Pidgey"; 
	        pidgey.setName(pokeName);
	        pidgey.setLevel(0);
	        pidgey.setShiny(false);
	        
	        
	        
	        if(pokemonRepository.findByName(pokeName).isEmpty()) {
	        pokemonRepository.save(pidgey);
	        System.out.println("Gespeichertes Pokémon: " + pidgey.getName());

	        // Pokémon aus der Datenbank laden
	        Pokemon foundPokemon = pokemonRepository.findByName(pokeName)
	                .orElseThrow(() -> new RuntimeException("Pokémon nicht gefunden!"));
	        System.out.println("Gefundenes Pokémon: " + foundPokemon.getName());
	        }
	        evoPokemonService.createPokemon(); 
	    }

}
