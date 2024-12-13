package de.apicall.application.entity;

import de.apicall.application.evolutions.EvolutionServiceController;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FirstEvoPokemon {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private EvolutionServiceController evoServiceController;
	
	public FirstEvoPokemon(EvolutionServiceController evoServiceController) {
		this.evoServiceController = evoServiceController; 
	}
	@Column(nullable=false)
	private String name; 
	
	@Column(nullable = false)
    private double level = 0;
	
	@Column(nullable=false)
	private boolean shiny;

	public Long getId() {
		return id;
	}
	public String getName() {
		return name; 
	}
	public void setName(String name) {
		this.name = evoServiceController.getRandomFirstStagePokemon();
	}
	public double getLevel() {
		return level; 
	}
	public void setLevel(double level) {
		this.level = level; 
	}
	public boolean isShiny() {
		return shiny;
	}

	public void setShiny(boolean shiny) {
		this.shiny = shiny;
	}
	public void addLevel(double increase) {
		this.level += increase; 
	}
			
			
			
}
