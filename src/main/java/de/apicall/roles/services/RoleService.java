package de.apicall.roles.services;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.Member;

public class RoleService {
	public void assignTrainerRole(Member member) {
        Role trainerRole = member.getGuild().getRolesByName("Trainer", true).stream()
                .findFirst()
                .orElse(null); 

        if (trainerRole != null) {
            member.getGuild().addRoleToMember(member, trainerRole).queue(
                success -> System.out.println("Rolle 'Trainer' wurde zugewiesen: " + member.getEffectiveName()),
                error -> System.err.println("Fehler beim Zuweisen der Rolle: " + error.getMessage())
            );
        } else {
            System.err.println("Rolle 'Trainer' existiert nicht im Server.");
        }
	}
}
