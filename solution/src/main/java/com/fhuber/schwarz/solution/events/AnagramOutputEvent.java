package com.fhuber.schwarz.solution.events;

import com.fhuber.schwarz.solution.service.AnagramStorageService;

public class AnagramOutputEvent {
    
   private AnagramStorageService dataService;

    public AnagramOutputEvent(AnagramStorageService dataService) {
        this.dataService = dataService;
    }
    
    public AnagramStorageService getAnagramStorageService() {
        return dataService;
    }
}
