package cz.cvut.fel.omo.sem.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class RoomFactoryTest {

    private Floor mockFloor;

    @BeforeEach
    public void setUp() {
        mockFloor = Mockito.mock(Floor.class);
    }

    @Test
    public void create_createsGarageRoom() {
        Room room = RoomFactory.create(RoomType.GARAGE, mockFloor);
        assertInstanceOf(Garage.class, room);
        assertEquals(RoomType.GARAGE, room.getRoomType());
        assertEquals(mockFloor, room.getFloor());
    }

    @Test
    public void create_createsLivingRoom() {
        Room room = RoomFactory.create(RoomType.LIVING_ROOM, mockFloor);
        assertInstanceOf(Room.class, room);
        assertEquals(RoomType.LIVING_ROOM, room.getRoomType());
        assertEquals(mockFloor, room.getFloor());
    }

    @Test
    public void create_createsKitchenRoom() {
        Room room = RoomFactory.create(RoomType.KITCHEN, mockFloor);
        assertInstanceOf(Room.class, room);
        assertEquals(RoomType.KITCHEN, room.getRoomType());
        assertEquals(mockFloor, room.getFloor());
    }

    @Test
    public void create_createsBedroomRoom() {
        Room room = RoomFactory.create(RoomType.BEDROOM, mockFloor);
        assertInstanceOf(Room.class, room);
        assertEquals(RoomType.BEDROOM, room.getRoomType());
        assertEquals(mockFloor, room.getFloor());
    }

    @Test
    public void create_createsBathroomRoom() {
        Room room = RoomFactory.create(RoomType.BATHROOM, mockFloor);
        assertInstanceOf(Room.class, room);
        assertEquals(RoomType.BATHROOM, room.getRoomType());
        assertEquals(mockFloor, room.getFloor());
    }

    @Test
    public void create_createsWorkRoom() {
        Room room = RoomFactory.create(RoomType.WORK_ROOM, mockFloor);
        assertInstanceOf(Room.class, room);
        assertEquals(RoomType.WORK_ROOM, room.getRoomType());
        assertEquals(mockFloor, room.getFloor());
    }
}
