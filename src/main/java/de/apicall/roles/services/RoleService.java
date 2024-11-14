package de.apicall.roles.services;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.entities.Member;

@Service
public class RoleService {
	
	private static RoleService instance; 
	
	private RoleService() {}
	
	public static synchronized RoleService getInstance() {
		if(instance == null) {
			instance = new RoleService(); 
		}
		return instance; 
	}
	
    /**
     * Generische Methode zum Zuweisen einer Rolle.
     * @param roleName Name der Rolle, die zugewiesen werden soll.
     * @param member Mitglied, dem die Rolle zugewiesen wird.
     * @param event Event, das den Kontext bereitstellt.
     */
	
	public void assignTrainerRole(Member member, MessageReceivedEvent event) {
        Role trainerRole = member.getGuild().getRolesByName("Trainer", true).stream()
                .findFirst()
                .orElse(null); 

        if (trainerRole == null) {
            // Erstelle die Rolle, falls sie nicht existiert
            event.getGuild().createRole()
                    .setName("Trainer")
                    .setMentionable(true)
                    .queue(role -> {
                        // Füge die Rolle nach Erstellung dem Mitglied hinzu
                        event.getGuild().addRoleToMember(member, role).queue(
                                success -> event.getChannel().sendMessage("Trainer-Rolle wurde erstellt und " + member.getEffectiveName() + " zugewiesen!").queue(),
                                error -> event.getChannel().sendMessage("Fehler beim Zuweisen der Rolle: " + error.getMessage()).queue()
                        );
                    }, error -> {
                        event.getChannel().sendMessage("Fehler beim Erstellen der Rolle: " + error.getMessage()).queue();
                    });
        } else {
            // Weise die existierende Rolle zu
            event.getGuild().addRoleToMember(member, trainerRole).queue(
                    success -> event.getChannel().sendMessage("Trainer-Rolle wurde erfolgreich zugewiesen an " + member.getEffectiveName()).queue(),
                    error -> event.getChannel().sendMessage("Fehler beim Zuweisen der Trainer-Rolle: " + error.getMessage()).queue()
            );
        }
	}
	
}
