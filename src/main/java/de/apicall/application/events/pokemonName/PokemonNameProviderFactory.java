package de.apicall.application.events.pokemonName;

import org.springframework.stereotype.Component;

@Component
public class PokemonNameProviderFactory {

	private final MessageServicePokemonNameProvider messageProvider;
    private final RequestQueryPokemonNameProvider queryProvider;

    public PokemonNameProviderFactory(
            MessageServicePokemonNameProvider messageProvider,
            RequestQueryPokemonNameProvider queryProvider) {
        this.messageProvider = messageProvider;
        this.queryProvider = queryProvider;
    }

    public PokemonNameProvider getProvider(String source) {
        switch (source) {
            case "message":
                return messageProvider;
            case "query":
                return queryProvider;
            default:
                throw new IllegalArgumentException("Invalid source: " + source);
        }
    }
}
