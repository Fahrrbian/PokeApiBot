package de.apicall.events;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Bot ist eingeloggt als " + event.getJDA().getSelfUser().getAsTag());
    }

}
