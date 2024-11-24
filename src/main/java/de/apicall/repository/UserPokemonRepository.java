package de.apicall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.apicall.entity.UserPokemon;
import de.apicall.entity.UserTable;
import net.dv8tion.jda.annotations.ReplaceWith;

@Repository
public interface UserPokemonRepository extends JpaRepository<UserPokemon, Long> {
    List<UserPokemon> findByUser(UserTable user);
}


