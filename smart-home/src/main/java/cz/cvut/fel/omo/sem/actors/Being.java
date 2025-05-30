package cz.cvut.fel.omo.sem.actors;

import cz.cvut.fel.omo.sem.house.Floor;
import cz.cvut.fel.omo.sem.house.Room;
import cz.cvut.fel.omo.sem.house.RoomType;
import cz.cvut.fel.omo.sem.interfaces.Updatable;

import java.util.List;
import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Abstract class representing a being in the house.
 */
public abstract class Being implements Updatable {

    /**
     * Name of the being.
     */
    protected final String name;
    /**
     * Age of the being.
     */
    protected final int age;
    /**
     * Room the being is currently in.
     */
    protected Room room;

    public Being(String name, int age, Room room) {
        this.name = name;
        this.age = age;
        this.room = room;
    }

    /**
     * Sets the room of the being and updates the room's items.
     * @param room new room
     */
    public void setRoom(Room room) {
        this.room.removeItem(Being.class, this);
        room.addItem(Being.class, this);
        this.room = room;
    }

    public Room getRoom() {
        return this.room;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * Method for being going to a random room besides garage in the house.
     */
    protected void goToRandomRoom() {
        logger.entering(Being.class.getSimpleName(), "goToRandomRoom");
        List<Room> rooms = room.getFloor().getHouse().getItems(Floor.class).stream()
                .flatMap(floor -> floor.getItems(Room.class).stream())
                .filter(room -> !room.getRoomType().equals(RoomType.GARAGE)).toList();
        setRoom(rooms.get(new Random().nextInt(rooms.size())));
        logger.exiting(Being.class.getSimpleName(), "goToRandomRoom");
    }

    /**
     * Abstract method for building a house configuration report for a being (person/pet).
     * @param sb StringBuilder to append the report to
     * @param tab number of tabs to indent the report
     */
    public abstract void buildHouseConfigurationReport(StringBuilder sb, int tab);
}
