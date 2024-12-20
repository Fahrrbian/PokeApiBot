package de.apicall.application.events.service;

import org.springframework.stereotype.Service;

import de.apicall.application.controller.EvolutionServiceController;
import de.apicall.application.entity.FirstEvoPokemon;
import de.apicall.application.entity.Pokemon;
import de.apicall.application.repository.EvolutionRepository;
import jakarta.transaction.Transactional;

@Service
public class FirstEvoPokemonService {

	private final EvolutionServiceController evolutionServiceController; 
	private final EvolutionRepository evolutionRepository; 
	private final PokemonGotNextEvo pokemonGotNextEvo; 
	private final EvolutionChainProcessor evoChainProcessor; 
	
	public FirstEvoPokemonService(EvolutionRepository evolutionRepository, 
			EvolutionServiceController evolutionServiceController, PokemonGotNextEvo pokemonGotNextEvo,
			EvolutionChainProcessor evoChainProcessor) {
		this.evolutionRepository = evolutionRepository; 
		this.evolutionServiceController = evolutionServiceController; 
		this.pokemonGotNextEvo = pokemonGotNextEvo; 
		this.evoChainProcessor = evoChainProcessor; 
	}
	@Transactional
	public  FirstEvoPokemon createPokemon() {
		String randomFirstStageName = evolutionServiceController.getRandomFirstStagePokemon(); 
		FirstEvoPokemon pokemon = new FirstEvoPokemon(); 
		pokemon.setName(randomFirstStageName);
		pokemon.setShiny(false);
		pokemon.setEvolutionLevel(16);
		return evolutionRepository.save(pokemon); 
	}
	public void feedPokemon(Pokemon pokemon, double levelPoints) {
		pokemon.addLevel(levelPoints);
		
		if(pokemon.getLevel() >= 16 && pokemonGotNextEvo.checkIfFirstEvo(pokemon.getName())) {
			String nextEvolution = evoChainProcessor.evolvePokemon(pokemon.getName());
			if(nextEvolution != null) {
				pokemon.setName(nextEvolution);
				pokemon.setLevel(0);
			}
		}
	}
}
