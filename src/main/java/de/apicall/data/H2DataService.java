 package de.apicall.data;

import de.apicall.entity.Pokemon;
import de.apicall.repository.PokemonRepository;


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
	private PokemonRepository pokemonRepository;
	
	 

	@Autowired
	public void setDependencies(PokemonRepository pokemonRepository) {
		 
		
		this.pokemonRepository = pokemonRepository; 
	}
	
	
	public void initializeData() {
		
		
	
		Pokemon pokemon = new Pokemon(); 
		pokemon.setName("bulbasaur"); 
		pokemon.setLevel(0);
		pokemon.setShiny(false);
		entityManager.persist(pokemon); 
		System.out.println("Gespeichert!");
		
		entityManager.flush();
		
		
		 
	}
	


	

	
}
