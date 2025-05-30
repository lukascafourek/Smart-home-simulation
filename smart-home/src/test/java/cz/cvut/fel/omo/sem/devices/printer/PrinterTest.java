package cz.cvut.fel.omo.sem.devices.printer;

import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.house.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {

    private Printer printer;

    @BeforeEach
    public void setUp() {
        Room mockRoom = Mockito.mock(Room.class);
        printer = new Printer(DeviceType.PRINTER, mockRoom);
    }

    @Test
    public void refillInkTest() {
        assertEquals(5, printer.getInkLevel());
        printer.setInkLevel(0);
        printer.refillInk();
        assertEquals(5, printer.getInkLevel());
    }
}
