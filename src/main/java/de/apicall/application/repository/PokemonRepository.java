package de.apicall.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.apicall.application.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
	Optional<Pokemon> findByName(String name);

}


