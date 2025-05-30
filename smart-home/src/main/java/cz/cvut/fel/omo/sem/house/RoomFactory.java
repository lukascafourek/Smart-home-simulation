package cz.cvut.fel.omo.sem.house;

/**
 * Factory class for creating types of rooms.
 */
public class RoomFactory {

    /**
     * Creates a room of a given type and floor.
     * @param roomType type of the room
     * @param floor    floor where the room is located
     * @return created room
     */
    public static Room create(RoomType roomType, Floor floor) {
        return switch (roomType) {
            case GARAGE -> new Garage(roomType, floor);
            case LIVING_ROOM, KITCHEN, BEDROOM, BATHROOM, WORK_ROOM -> new Room(roomType, floor);
        };
    }
}
