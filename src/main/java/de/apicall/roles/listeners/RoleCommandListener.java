package de.apicall.roles.listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import de.apicall.commands.BotCommand;

public class RoleCommandListener extends ListenerAdapter implements BotCommand {

	 @Override
	    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
	        String message = event.getMessage().getContentRaw();
	        String[] args = message.split(" ");  
	        if (args[0].equalsIgnoreCase("!trainer")) {
	            if (args.length < 2) {
	                event.getChannel().sendMessage("Bitte erwähne einen Benutzer, um die Trainer-Rolle zuzuweisen!").queue();
	                return;
	            }

	            // Extrahiere die Benutzer-ID aus der Nachricht
	            String userId = args[1].replaceAll("[^0-9]", "");  // Entferne Sonderzeichen (<@!1234567890>)
	            Member member = event.getGuild().getMemberById(userId);

	            if (member != null) {
	                assignTrainerRole(member, event);
	            } else {
	                event.getChannel().sendMessage("Benutzer konnte nicht gefunden werden.").queue();
	            }
	        }
	    }

	    private void assignTrainerRole(Member member, MessageReceivedEvent event) {
	        Role trainerRole = event.getGuild().getRolesByName("Trainer", true).stream()
	                .findFirst()
	                .orElse(null);

	        if (trainerRole != null) {
	            event.getGuild().addRoleToMember(member, trainerRole).queue(
	                success -> event.getChannel().sendMessage("Trainer-Rolle wurde erfolgreich zugewiesen an " + member.getEffectiveName()).queue(),
	                error -> event.getChannel().sendMessage("Fehler beim Zuweisen der Trainer-Rolle: " + error.getMessage()).queue()
	            );
	        } else {
	            event.getChannel().sendMessage("Die Rolle 'Trainer' existiert nicht. Bitte erstelle sie zuerst.").queue();
	        }
	    }

		@Override
		public void register(JDA jda) {
			// TODO Auto-generated method stub
			jda.addEventListener(this);
			
		}
	}
