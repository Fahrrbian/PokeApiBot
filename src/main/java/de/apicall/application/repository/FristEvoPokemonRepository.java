package de.apicall.application.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.apicall.application.entity.FirstEvoPokemon;

public interface FristEvoPokemonRepository extends JpaRepository<FirstEvoPokemon, Long>{

	@Query(value = "SELECT * FROM first_evo_pokemon ORDER BY id DESC LIMIT 1", nativeQuery =true)
	 FirstEvoPokemon findFirstByOrderByIdDesc(); 
	
}
