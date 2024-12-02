package de.apicall.application.roles.enums;

import java.util.List;

public enum RoleType {
	TRAINER(List.of("view_pokedex", "start_battle")); 
	
	private final List<String> permissions; 
	
	RoleType(List<String> permissions) {
		this.permissions = permissions; 
	}
	public List<String> getPermissions() {
		return permissions; 
	}
	public boolean hasPermission(String permission) {
		return permissions.contains(permission); 
	}
}
