package de.apicall.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.apicall.entity.UserTable;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
	Optional<UserTable> findByDiscordId(String discordId); 
}
