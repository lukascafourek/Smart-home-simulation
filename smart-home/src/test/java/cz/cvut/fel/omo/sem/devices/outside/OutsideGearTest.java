package cz.cvut.fel.omo.sem.devices.outside;

import cz.cvut.fel.omo.sem.actors.people.Person;
import cz.cvut.fel.omo.sem.house.Garage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OutsideGearTest {

    private OutsideGear outsideGear;
    private Person mockPerson;

    @BeforeEach
    public void setUp() {
        Garage mockRoom = Mockito.mock(Garage.class);
        outsideGear = new OutsideGear(OutsideGearType.BIKE, mockRoom);
        mockPerson = Mockito.mock(Person.class);
    }

    @Test
    public void goOutsideTest() {
        outsideGear.goOutside(mockPerson);
        assertEquals(mockPerson, outsideGear.getPerson());
    }

    @Test
    public void stopUsingTest() {
        outsideGear.goOutside(mockPerson);
        outsideGear.stopUsing();
        assertNull(outsideGear.getPerson());
    }
}
