package de.apicall.data;

import de.apicall.entity.Pokemon;
import de.apicall.entity.User;
import de.apicall.entity.UserPokemon;
import de.apicall.repository.PokemonRepository;
import de.apicall.repository.UserPokemonRepository;
import de.apicall.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.transaction.Transactional;

@Service
@Transactional
public class H2DataService {
	
	 
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PokemonRepository pokemonRepository;
	@Autowired
	private UserPokemonRepository userPokemonRepository; 

	@Autowired
	public void setDependencies(UserRepository userRepository, PokemonRepository pokemonRepository, UserPokemonRepository userPokemonRepository) {
		this.userRepository = userRepository; 
		this.userPokemonRepository = userPokemonRepository;  
		this.pokemonRepository = pokemonRepository; 
	}

	
	public void initializeData() {
		User user = new User(); 
		Pokemon pokemon = new Pokemon(); 
		UserPokemon userPokemon = new UserPokemon(); 
	}
}
