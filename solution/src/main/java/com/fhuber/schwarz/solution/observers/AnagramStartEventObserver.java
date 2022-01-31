package com.fhuber.schwarz.solution.observers;

import com.fhuber.schwarz.solution.events.AnagramStartEvent;
import com.fhuber.schwarz.solution.service.AnagramService;

import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;

public class AnagramStartEventObserver {
    public String onEvent(@Observes @Priority(1) AnagramStartEvent event, AnagramService anagramService) {
        return anagramService.process(event.getEventMessage());
    }
}
