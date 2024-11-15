package de.apicall.entity;

import jakarta.persistence.*;

@Entity
public class Pokemon {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long pokemonId; 
	
	@Column(nullable = false)
	private String name; 
	
	@Column(nullable=false)
	private boolean shiny;

	public Long getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(Long pokemonId) {
		this.pokemonId = pokemonId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isShiny() {
		return shiny;
	}

	public void setShiny(boolean shiny) {
		this.shiny = shiny;
	} 
	
	
}
