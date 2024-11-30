package de.apicall.cmdlinernr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import de.apicall.entity.Pokemon;
import de.apicall.repository.PokemonRepository;


//@Order(2)
@Component
public class PokemonCommandLineRunner implements CommandLineRunner{

	private final PokemonRepository pokemonRepository; 
	
	public PokemonCommandLineRunner(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository; 
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		 Pokemon pikachu = new Pokemon();
	        pikachu.setName("Pikachu");
	        pikachu.setLevel(5);
	        pikachu.setShiny(true);

	        pokemonRepository.save(pikachu);
	        System.out.println("Gespeichertes Pokémon: " + pikachu.getName());

	        // Pokémon aus der Datenbank laden
	        Pokemon foundPokemon = pokemonRepository.findByName("Pikachu")
	                .orElseThrow(() -> new RuntimeException("Pokémon nicht gefunden!"));
	        System.out.println("Gefundenes Pokémon: " + foundPokemon.getName() + ", Level: " + foundPokemon.getLevel());
	    }

}
