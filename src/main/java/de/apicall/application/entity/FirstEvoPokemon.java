package de.apicall.application.entity;


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

	@Column(nullable=false)
	private String name; 
	
	@Column(nullable = false)
    private double level = 0;
	
	@Column(nullable=false)
	private boolean shiny;
	
	@Column(nullable=false)
	private String nextEvolutionName;
	
	@Column(nullable=false)
	private int evolutionLevel; 
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name; 
	}
	public void setName(String name) {
		this.name = name; 
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
	public String getNextEvolutionName() {
		return nextEvolutionName;
	}
	public void setNextEvolutionName(String nextEvolutionName) {
		this.nextEvolutionName = nextEvolutionName; 
	}
	public int getEvolutionLevel() {
		return evolutionLevel;
	}
	public void setEvolutionLevel(int evolutionLevel) {
		this.evolutionLevel = evolutionLevel; 
	}
	public void addLevel(double increase) {
		this.level += increase; 
	}
			
			
			
}
