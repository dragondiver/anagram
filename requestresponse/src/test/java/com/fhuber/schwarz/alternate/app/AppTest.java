package com.fhuber.schwarz.alternate.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.security.Permission;

import com.fhuber.schwarz.alternate.service.Anagram2Service;
import com.fhuber.schwarz.alternate.service.impl.Anagram2FileService;
import com.fhuber.schwarz.solution.exception.AnagramException;
import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.service.impl.AnagramMapStorage;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@EnableAutoWeld
public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    protected static class ExitException extends SecurityException {
        public final int status;

        public ExitException(int status) {
            super("There is no escape!");
            this.status = status;
        }
    }

    private static class NoExitSecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            // allow anything.
        }

        @Override
        public void checkPermission(Permission perm, Object context) {
            // allow anything.
        }

        @Override
        public void checkExit(int status) {
            throw new ExitException(status);
        }
    }

    @WeldSetup
    public WeldInitiator weld = WeldInitiator
            .of(WeldInitiator.createWeld()
                    .addPackages(Anagram2Service.class, AnagramMap.class)
                    .addBeanClasses(AnagramMapStorage.class, Anagram2FileService.class));

    // static Bean<?> createSelectedAlternativeBean() {
    // return
    // MockBean.builder().types(AnagramOutputService.class).selectedAlternative(AnagramSystemWriterService.class)
    // .creating(new AnagramSystemWriterService()).build();
    // }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setSecurityManager(new NoExitSecurityManager());

    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setSecurityManager(null);
    }

    @BeforeAll
    public static void setup() {
        // String path = AnagramApp.class.getClassLoader()
        // .getResource("logging.properties")
        // .getFile();
        // System.setProperty("java.util.logging.config.file", path);
        // System.setProperty("key", value)
    }

    @Test
    public void shouldThrowException() {
        String[] args = { "test" };
        AnagramException thrown = assertThrows(AnagramException.class, () -> {
            AnagramApp.main(args);
        });
        assertEquals("File not found", thrown.getMessage());
    }

    @Test
    public void shouldRefuseExecution() {
        String[] args = { "" };
        ExitException thrown = assertThrows(ExitException.class, () -> {
            AnagramApp.main(args);

        });
        assertEquals(1, thrown.status);
        assertEquals("Proper Usage is: java program filename", errContent.toString().trim());
    }

    @Test
    public void shouldWork() {
        URL url = this.getClass().getResource("sample.txt");
        String path = url.getPath();
        String[] args = { path };
        assertDoesNotThrow(() -> {
            AnagramApp.main(args);
        });
        assertEquals("act cat", outContent.toString().trim());
    }

}
