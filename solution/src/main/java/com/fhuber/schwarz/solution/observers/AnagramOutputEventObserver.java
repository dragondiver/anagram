package com.fhuber.schwarz.solution.observers;

import com.fhuber.schwarz.solution.events.AnagramOutputEvent;
import com.fhuber.schwarz.solution.service.AnagramOutputService;

import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;

public class AnagramOutputEventObserver {
    public void onEvent(@Observes @Priority(1) AnagramOutputEvent event, AnagramOutputService anagramOutputService) {
        anagramOutputService.print(event.getAnagramStorageService());
    } 
}
