package de.apicall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="pokemon")
public class Pokemon {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long pokemonId; 
	
	@Column(nullable = false)
	private String name; 
	
	@Column(nullable = false)
    private int level;
	
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
	public int getLevel() {
		return level; 
	}
	public void setLevel(int level) {
		this.level = level; 
	}
	public boolean isShiny() {
		return shiny;
	}

	public void setShiny(boolean shiny) {
		this.shiny = shiny;
	} 
}
