package com.fhuber.schwarz.solution.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fhuber.schwarz.solution.app.AnagramSystemWriterService;
import com.fhuber.schwarz.solution.events.AnagramStartEvent;
import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.model.Word;
import com.fhuber.schwarz.solution.observers.AnagramOutputEventObserver;
import com.fhuber.schwarz.solution.service.AnagramService;
import com.fhuber.schwarz.solution.service.AnagramStorage;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

@EnableAutoWeld
public class AnagramMapStorageTest {

    @Inject
    AnagramStorage storage;

    @WeldSetup
    public WeldInitiator weld = WeldInitiator
            .of(WeldInitiator.createWeld()
                    .addPackages(AnagramStartEvent.class, AnagramOutputEventObserver.class, AnagramService.class, AnagramMap.class)
                    .addBeanClasses(AnagramMapStorage.class, AnagramFileService.class,
                            AnagramSystemWriterService.class).addAlternative(AnagramSystemWriterService.class));

    @Test
    public void shouldSave() {
        Word word = new Word("cat");
        storage.save(word);
        assertEquals("cat",storage.getAnagramMap().getAnaMap().get("act").getAnagramsAsString());
    }

}
