package de.apicall.config;

import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import de.apicall.commands.ApiCommand;
import de.apicall.controller.ApiController;
import de.apicall.evolutions.EvolutionController;
import de.apicall.repository.PokemonRepository;
import de.apicall.repository.PokemonRepositoryCustomImpl;
import de.apicall.repository.UserPokemonRepository;
import de.apicall.repository.UserPokemonRepositoryCustomImpl;
import de.apicall.repository.UserRepository;
import de.apicall.repository.UserRepositoryCustomImpl;
import de.apicall.roles.config.RoleConfigLoader;
import de.apicall.roles.listeners.RoleCommandListener;
import de.apicall.roles.services.RoleService;
import de.apicall.services.MessageService;
import de.apicall.services.PokemonService;
import de.apicall.test.H2DatabaseTest;


@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApiCommand apiCommand(RestTemplate restTemplate, MessageService messageService, ApiController apiController, EvolutionController evocontroller, RoleConfigLoader roleConfig) {
        return new ApiCommand(restTemplate, messageService, apiController, evocontroller, roleConfig);
    }
    @Bean 
    public RoleCommandListener roleCommandListener(RoleService roleService, PokemonService pokemonService, H2DatabaseTest h2test) {
    	return new RoleCommandListener(roleService, pokemonService, h2test); 
    }
    @Bean 
    public PokemonRepository pokemonRepository() {
        return new PokemonRepositoryCustomImpl(); 
    }
    @Bean 
    public UserRepository userRepository() {
    	return new UserRepositoryCustomImpl(); 
    }
    @Bean 
    public UserPokemonRepository userPokemonRepository() {
    	return new UserPokemonRepositoryCustomImpl(); 
    }
    @Bean 
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (DataSource dataSource, JpaProperties jpaProperties) {
    	LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    	factoryBean.setDataSource(dataSource);
    	factoryBean.setPackagesToScan("de.apicall.entity");
    	factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    	factoryBean.setJpaPropertyMap(jpaProperties.getProperties()); 
    	return factoryBean; 
    }
}
