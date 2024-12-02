package de.apicall.application.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetCommand extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event ) {
	
	if(event.isFromGuild()) {
	  if (event.getMessage().getContentRaw().equals("!getdata")) {
          event.getChannel().sendMessage("Daten werden geladen...").queue();

	HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.example.com/data")) // Beispiel-URL
            .build();
	   // Asynchroner GET-Request
    client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(response -> {
                // Antwort in den Discord-Kanal senden
                event.getChannel().sendMessage("Antwort von der API: " + response).queue();
            })
            .exceptionally(e -> {
                // Fehlerbehandlung
                event.getChannel().sendMessage("Es gab einen Fehler bei der Anfrage: " + e.getMessage()).queue();
                return null;
           		});
	  		}
		}
	}
}