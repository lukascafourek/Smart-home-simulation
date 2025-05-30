package cz.cvut.fel.omo.sem.house;

import cz.cvut.fel.omo.sem.interfaces.Updatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a floor in a house.
 */
public class Floor extends SpaceAggregateImpl implements Updatable {

    private final int level;
    private final House house;
    private final List<Room> rooms = new ArrayList<>();

    public Floor(int level, House house) {
        this.level = level;
        this.house = house;
        collections.put(Room.class, rooms);
    }

    void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        sb.append("\t".repeat(tab)).append(getClass().getSimpleName()).append(" level ").append(level).append("\n");
        rooms.forEach(x -> x.buildHouseConfigurationReport(sb, tab + 1));
    }

    @Override
    public void update() {
        rooms.forEach(Room::update);
    }

    public House getHouse() {
        return house;
    }
}
