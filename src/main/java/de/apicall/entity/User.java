package de.apicall.entity;

import java.util.List;

import javax.persistence.*;


@Entity 
@Table(name = "USERTABLE")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long userId; 
	
	@Column(nullable=false, unique= true)
	private String discordId; 
	
	@Column(nullable=false)
	private String username; 
	
	@OneToMany(mappedBy="user")
	private List<UserPokemon> userPokemons;

	public Long getUserId() {
		return userId;
	}
/*
	public void setUserId(Long userId) {
		this.userId = userId;
	}
*/
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
