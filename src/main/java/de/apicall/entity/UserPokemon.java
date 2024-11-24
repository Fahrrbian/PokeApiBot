package de.apicall.entity;

import java.time.LocalDateTime;

import javax.persistence.*;


@Entity
public class UserPokemon {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userPokemonId; 
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private UserTable user; 
	
	@ManyToOne
	@JoinColumn(name= "pokemon_id", nullable=false)
	private Pokemon pokemon; 
	
	public Long getUserPokemonId() {
		return userPokemonId;
	}

	public void setUserPokemonId(Long userPokemonId) {
		this.userPokemonId = userPokemonId;
	}

	public UserTable getUser() {
		return user;
	}

	public void setUser(UserTable user) {
		this.user = user;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public LocalDateTime getCaughtDate() {
		return caughtDate;
	}

	public void setCaughtDate(LocalDateTime caughtDate) {
		this.caughtDate = caughtDate;
	}

	@Column(nullable = false)
	private LocalDateTime caughtDate;
}
