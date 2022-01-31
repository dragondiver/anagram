package com.fhuber.schwarz.solution.observers;

import com.fhuber.schwarz.solution.events.AnagramStartEvent;
import com.fhuber.schwarz.solution.service.AnagramService;

import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;

/**
 * if an Event is received, send the AnagramOutputService (discovered by
 * Container)
 * the message process with argument filename
 * 
 * @author Florian Huber
 */
public class AnagramStartEventObserver {
    public String onEvent(@Observes @Priority(1) AnagramStartEvent event, AnagramService anagramService) {
        return anagramService.process(event.getFilename());
    }
}
