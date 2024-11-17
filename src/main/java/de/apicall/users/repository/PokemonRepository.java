package de.apicall.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.apicall.entity.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByName(String name);
}


