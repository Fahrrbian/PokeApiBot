package de.apicall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

private final RestTemplate restTemplate;

@Bean
public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
}


@Autowired
public ApiController(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
}

@GetMapping("/data")
public ResponseEntity<String> getData() {
    String url = "https://jsonplaceholder.typicode.com/todos/1";
    String response = restTemplate.getForObject(url, String.class);
    return ResponseEntity.ok(response);
	}
}

