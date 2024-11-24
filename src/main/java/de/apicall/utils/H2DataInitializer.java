package de.apicall.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import de.apicall.data.H2DataService;

@Component
public class H2DataInitializer {

    @Autowired
    private H2DataService h2DataService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        h2DataService.initializeData();
    }
}