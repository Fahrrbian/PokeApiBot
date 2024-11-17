package de.apicall.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.apicall.entity.User;
import de.apicall.entity.UserPokemon;

public interface UserPokemonRepository extends JpaRepository<UserPokemon, Long> {
    List<UserPokemon> findByUser(User user);
}


