package de.apicall.roles.config;

import java.io.InputStream;
import java.util.Map;

import org.springframework.stereotype.Component;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component 
public class RoleConfigLoader {

	private Map<String, List<String>> rolePermissions; 
	
	public RoleConfigLoader() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper(); 
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("roles-config.json")) {
			if(inputStream == null) {
				throw new IllegalArgumentException("roles-config.json nicht gefunden"); 
			}
			rolePermissions = objectMapper.readValue(inputStream, Map.class); 
		}
	}
	
	public List<String> getPermissionsForRole(String roleName) {
		return rolePermissions.getOrDefault(roleName, List.of()); 
	}
}
