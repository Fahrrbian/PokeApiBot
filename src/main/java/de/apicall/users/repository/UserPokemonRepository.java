package de.apicall.users.repository;

public interface UserPokemonRepository extends JpaRepository<UserPokemon, Long> {
    List<UserPokemon> findByUser(User user);
}

}
