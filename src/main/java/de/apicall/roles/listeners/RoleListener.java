package de.apicall.roles.listeners;

import de.apicall.roles.services.RoleService;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import org.jetbrains.annotations.NotNull;


public class RoleListener extends ListenerAdapter {

	private final RoleService roleService = new RoleService(); 
	
	@Override
	    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
	        Member newMember = event.getMember();
	        roleService.assignTrainerRole(newMember);  
	    }
	
}
