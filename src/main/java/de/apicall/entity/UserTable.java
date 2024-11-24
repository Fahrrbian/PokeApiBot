package de.apicall.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
public class UserTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long userId; 
	
	@Column(nullable=false, unique= true)
	private String discordId  = "DEFAULT_DISCORD_ID"; 
	
	@Column(nullable=false)
	private String username = "DEFAULT_USERNAME"; 
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval =true)
	private List<UserPokemon> userPokemons = new ArrayList<>();
 
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDiscordId() {
		return discordId;
	}

	public void setDiscordId(String discordId) {
		this.discordId = discordId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserPokemon> getUserPokemons() {
		return userPokemons;
	}

	public void setUserPokemons(List<UserPokemon> userPokemons) {
		this.userPokemons = userPokemons;
	} 
}
