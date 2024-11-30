package de.apicall.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.apicall.entity.Pokemon;
import de.apicall.repository.PokemonRepository;

@Service
public class PokemonService {
/*
	private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Optional<Pokemon> findPokemonByName(String name) {
        return pokemonRepository.findByName(name);
    }

    public List<Pokemon> findAllPokemon() {
        return pokemonRepository.findAll();
    }

    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }*/
}

