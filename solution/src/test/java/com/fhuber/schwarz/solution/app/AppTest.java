package com.fhuber.schwarz.solution.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.security.Permission;

import com.fhuber.schwarz.solution.events.AnagramStartEvent;
import com.fhuber.schwarz.solution.exception.AnagramException;
import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.observers.AnagramOutputEventObserver;
import com.fhuber.schwarz.solution.service.AnagramService;
import com.fhuber.schwarz.solution.service.impl.AnagramFileService;
import com.fhuber.schwarz.solution.service.impl.AnagramMapStorage;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.UnboundLiteral;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@EnableAutoWeld
@EnableAlternatives(AnagramSystemWriterService.class)
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
                    .addPackages(AnagramStartEvent.class, AnagramOutputEventObserver.class, AnagramService.class, AnagramMap.class)
                    .addBeanClasses(AnagramMapStorage.class, AnagramFileService.class,
                            AnagramSystemWriterService.class).addAlternative(AnagramSystemWriterService.class));

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
        RequestContext requestContext = weld.select(RequestContext.class, UnboundLiteral.INSTANCE).get();
            requestContext.activate();
        assertDoesNotThrow(() -> {
            weld.event().select(AnagramStartEvent.class).fire(new AnagramStartEvent(path));
        });
        assertEquals("act cat", outContent.toString().trim());
    }

}
