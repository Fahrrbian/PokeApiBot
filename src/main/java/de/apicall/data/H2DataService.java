package de.apicall.data;

import de.apicall.entity.Pokemon;
import de.apicall.entity.UserPokemon;
import de.apicall.entity.UserTable;
import de.apicall.repository.PokemonRepository;
import de.apicall.repository.UserPokemonRepository;
import de.apicall.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class H2DataService {
	
	 
	@PersistenceContext
	private EntityManager entityManager; 

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
		createUserDirectly();
		/*
		User user = new User(); 
		user.setDiscordId("783795730757976075");
		user.setUsername("ADMIN");
		entityManager.persist(user); 
		
		Pokemon pokemon = new Pokemon(); 
		pokemon.setName("bulbasaur"); 
		pokemon.setLevel(0);
		pokemon.setShiny(false);
		//entityManager.persist(pokemon); 
		System.out.println("Gespeichert!");
		
		entityManager.flush();
		
		
		UserPokemon userPokemon = new UserPokemon(); */ 
	}
	public void createUserDirectly() {
	    UserTable user = new UserTable();
	    user.setDiscordId("123456789");
	    user.setUsername("TestUser");
	    entityManager.persist(user);

	    entityManager.flush();
	    System.out.println("User direkt gespeichert!");
	}
}
