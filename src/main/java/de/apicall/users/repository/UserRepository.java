package de.apicall.users.repository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByDiscordId(String discordId); 
}
