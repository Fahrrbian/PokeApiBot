package de.apicall.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.apicall.application.entity.FirstEvoPokemon;

public interface FristEvoPokemonRepository extends JpaRepository<FirstEvoPokemon, Long>{

	@Query("SELECT f FROM FirstEvoPokemon f ORDER BY f.id DESC")
	Optional<FirstEvoPokemon> findMostRecent(); 
	
}
