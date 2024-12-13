package de.apicall.application.repository;

import org.springframework.stereotype.Repository;
import de.apicall.application.entity.FirstEvoPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface EvolutionRepository extends JpaRepository<FirstEvoPokemon, Long>{
Optional<FirstEvoPokemon> findByName(String name); 
}
