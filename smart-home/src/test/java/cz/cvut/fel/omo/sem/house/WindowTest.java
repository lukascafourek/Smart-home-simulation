package cz.cvut.fel.omo.sem.house;

import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class WindowTest {

    private Window window;
    private Event<?> mockEvent;

    @BeforeEach
    public void setUp() {
        window = new Window();
        mockEvent = Mockito.mock(Event.class);
    }

    @Test
    public void updateEvent_togglesWindowStateOnWindEvent() {
        Mockito.when(mockEvent.getEventType()).thenReturn(EventType.WIND);
        assertFalse(window.isOpen());
        window.updateEvent(mockEvent);
        assertTrue(window.isOpen());
        window.updateEvent(mockEvent);
        assertFalse(window.isOpen());
    }
}
