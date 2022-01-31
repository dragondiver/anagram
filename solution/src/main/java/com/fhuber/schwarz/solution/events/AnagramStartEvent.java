package com.fhuber.schwarz.solution.events;

public class AnagramStartEvent {
    
   private String eventMessage;

    public AnagramStartEvent(String eventMessage) {
        this.eventMessage = eventMessage;
    }
    
    public String getEventMessage() {
        return eventMessage;
    }
}
