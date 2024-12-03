package de.apicall.application.roles.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CommandName {
	 	POKEMON("!pokemon", "Zeigt Informationen zu Pokemon an"),
	    COMMANDS("!commands", "Listet alle verfügbaren Commands auf"),
	    ABILITIES("!abilities", "Zeigt Fähigkeiten eines Pokemons an"),
	    EVOLVE("!evolution", "Entwickelt ein Pokemon weiter"),
		PERMISSION("!permission", "Ruft Berechtigung ab"); 
		

	    private final String command;
	    private final String description;

	    CommandName(String command, String description) {
	        this.command = command;
	        this.description = description;
	    }

	    public String getCommand() {
	        return command;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public static List<String> getAllCommands() {
	        return Stream.of(CommandName.values())
	                     .map(CommandName::getCommand)
	                     .collect(Collectors.toList());
	    }
	    public static List<String> getAllCommandsWithDescriptions() {
	        return Stream.of(CommandName.values())
	                     .map(cmd -> cmd.getCommand() + " - " + cmd.getDescription())
	                     .collect(Collectors.toList());
	    }
}
