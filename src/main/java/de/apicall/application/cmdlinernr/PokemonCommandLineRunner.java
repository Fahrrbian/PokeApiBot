package de.apicall.application.cmdlinernr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import de.apicall.application.entity.Pokemon;
import de.apicall.application.repository.PokemonRepository;


//@Order(2)
@Component
public class PokemonCommandLineRunner implements CommandLineRunner{

	@Autowired
	private final PokemonRepository pokemonRepository; 
	
	public PokemonCommandLineRunner(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository; 
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PokemonRepository bean injected: " + (pokemonRepository != null));

		 Pokemon pikachu = new Pokemon();
	        pikachu.setName("Pikachu");
	      

	        pokemonRepository.save(pikachu);
	        System.out.println("Gespeichertes Pokémon: " + pikachu.getName());

	        // Pokémon aus der Datenbank laden
	        Pokemon foundPokemon = pokemonRepository.findByName("Pikachu")
	                .orElseThrow(() -> new RuntimeException("Pokémon nicht gefunden!"));
	        System.out.println("Gefundenes Pokémon: " + foundPokemon.getName());
	    }

}
