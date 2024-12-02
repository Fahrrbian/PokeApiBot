package de.apicall.application.roles.listeners;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import org.jetbrains.annotations.NotNull;

import de.apicall.application.roles.services.RoleService;


public class RoleListener extends ListenerAdapter {

	private final RoleService roleService = RoleService.getInstance(); 	 
	
	@Override
	    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
	        Member newMember = event.getMember();
	        roleService.assignTrainerRole(newMember, null);  
	    }
	
}
