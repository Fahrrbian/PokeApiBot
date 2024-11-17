package de.apicall.services;

import org.springframework.stereotype.Service;

import de.apicall.entity.User;
import de.apicall.entity.UserPokemon;
import de.apicall.entity.Pokemon;
import de.apicall.users.repository.UserPokemonRepository;
import de.apicall.users.repository.UserRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PokemonService {
	 	private final UserRepository userRepository;
	    private final UserPokemonRepository userPokemonRepository;

	    public PokemonService(UserRepository userRepository, UserPokemonRepository userPokemonRepository) {
	        this.userRepository = userRepository;
	        this.userPokemonRepository = userPokemonRepository;
	    }

	    /**
	     * Holt alle Pokémon eines Benutzers anhand der Discord-ID.
	     * @param discordId Die Discord-ID des Benutzers.
	     * @return Liste der Pokémon als formatierte Strings.
	     */
	    public List<String> getPokemonsForUser(String discordId) {
	        User user = userRepository.findByDiscordId(discordId)
	                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));

	        List<UserPokemon> userPokemons = userPokemonRepository.findByUser(user);

	        return userPokemons.stream()
	                .map(userPokemon -> {
	                    Pokemon pokemon = userPokemon.getPokemon();
	                    return pokemon.getName() + " (Level: " + pokemon.getLevel() + (pokemon.isShiny() ? ", Shiny" : "") + ")";
	                })
	                .collect(Collectors.toList());
	    }
	
}
