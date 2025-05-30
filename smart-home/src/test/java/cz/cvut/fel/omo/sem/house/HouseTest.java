package cz.cvut.fel.omo.sem.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {

    private House house;
    private Floor mockFloor;

    @BeforeEach
    public void setUp() {
        house = House.getInstance();
        mockFloor = Mockito.mock(Floor.class);
    }

    @Test
    public void addItem_getItems_addsAndGetsItemToCollection() {
        List<Floor> items = new ArrayList<>();
        house.addItem(Floor.class, mockFloor);
        items.add(mockFloor);
        assertEquals(items, house.getItems(Floor.class));
    }

    @Test
    public void removeItem_removesItemFromCollection() {
        house.addItem(Floor.class, mockFloor);
        house.removeItem(Floor.class, mockFloor);
        assertTrue(house.getItems(Floor.class).isEmpty());
    }

    @Test
    public void addItem_throwsExceptionForUnsupportedType() {
        assertThrows(IllegalArgumentException.class, () -> house.addItem(Integer.class, 1));
    }

    @Test
    public void removeItem_throwsExceptionForUnsupportedType() {
        assertThrows(IllegalArgumentException.class, () -> house.removeItem(Integer.class, 1));
    }

    @Test
    public void getItems_returnsNullForUnsupportedType() {
        assertNull(house.getItems(Integer.class));
    }
}
