package com.fhuber.schwarz.alternate.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.model.Word;
import com.fhuber.schwarz.solution.service.AnagramStorage;
import com.fhuber.schwarz.solution.service.impl.AnagramMapStorage;

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
                    .addPackages(AnagramMap.class)
                    .addBeanClasses(AnagramMapStorage.class));

    @Test
    public void shouldSave() {
        Word word = new Word("cat");
        storage.save(word);
        assertEquals("cat", storage.getAnagramMap().getAnaMap().get("act").getAnagramsAsString());
    }

}
