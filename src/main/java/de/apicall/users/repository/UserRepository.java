package de.apicall.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.apicall.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByDiscordId(String discordId); 
}
