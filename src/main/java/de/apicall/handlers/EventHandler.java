package de.apicall.handlers;

import de.apicall.events.ReadyListener;
import net.dv8tion.jda.api.JDA;

public class EventHandler {
    public EventHandler(JDA jda) {
        jda.addEventListener(new ReadyListener());
        // Weitere Events hinzufügen
    }

}
