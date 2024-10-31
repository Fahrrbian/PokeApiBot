package de.apicall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@RestController
@RequestMapping("/api")
public class ApiController {

private final RestTemplate restTemplate;



@Autowired
public ApiController(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
}

@GetMapping("/pokemon") 
public ResponseEntity<String> getData() {
	String url ="https://pokeapi.co/api/v2/pokemon/ditto"; 
	String response = restTemplate.getForObject(url, String.class);
	
	 JSONObject jsonResponse = new JSONObject(response);
     String imageUrl = jsonResponse
                         .getJSONObject("sprites")
                         .getString("front_default");

     return ResponseEntity.ok(imageUrl);
	
	}
}

