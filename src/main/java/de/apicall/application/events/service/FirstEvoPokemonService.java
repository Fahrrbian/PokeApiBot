package de.apicall.application.events.service;

import org.springframework.stereotype.Service;

import de.apicall.application.controller.EvolutionServiceController;
import de.apicall.application.entity.FirstEvoPokemon;
import de.apicall.application.repository.EvolutionRepository;

@Service
public class FirstEvoPokemonService {

	private final EvolutionServiceController evolutionServiceController; 
	private final EvolutionRepository evolutionRepository; 
	
	public FirstEvoPokemonService(EvolutionRepository evolutionRepository, EvolutionServiceController evolutionServiceController) {
		this.evolutionRepository = evolutionRepository; 
		this.evolutionServiceController = evolutionServiceController; 
	}
	public  FirstEvoPokemon createPokemon() {
		String randomFirstStageName = evolutionServiceController.getRandomFirstStagePokemon(); 
		FirstEvoPokemon pokemon = new FirstEvoPokemon(); 
		pokemon.setName(randomFirstStageName);
		pokemon.setShiny(false);
		return evolutionRepository.save(pokemon); 
	}
}
