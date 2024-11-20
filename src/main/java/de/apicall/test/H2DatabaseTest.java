package de.apicall.test;
import de.apicall.entity.UserPokemon;
import de.apicall.entity.Pokemon;
import de.apicall.entity.User;
import de.apicall.repository.UserPokemonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;



import java.time.LocalDateTime;

@Service
@Transactional
public class H2DatabaseTest {
/*
    @Autowired
    private UserPokemonRepository userPokemonRepository;

    @Autowired
    private EntityManager entityManager; 
    
    @Test
    public void testSaveAndRetrieveUserPokemon() {
        // 1. Testdaten erstellen
        User user = new User();
        user.setUsername("Ash Ketchum");

        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pikachu");
        pokemon.setLevel(25);
        pokemon.setShiny(true);

        UserPokemon userPokemon = new UserPokemon();
        userPokemon.setUser(user);
        userPokemon.setPokemon(pokemon);
        userPokemon.setCaughtDate(LocalDateTime.now());

        // 2. Speichern
        UserPokemon savedUserPokemon = userPokemonRepository.save(userPokemon);

        entityManager.flush(); 
        entityManager.persist(pokemon); 
        
        System.out.println("UserPokemon erfolgreich gespeichert: " + savedUserPokemon);
    }*/
}