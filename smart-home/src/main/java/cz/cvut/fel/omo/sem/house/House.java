package cz.cvut.fel.omo.sem.house;

import cz.cvut.fel.omo.sem.interfaces.Updatable;

import java.util.ArrayList;
import java.util.List;

/**
 * House is an aggregate of floors.
 * It is a singleton class.
 */
public class House extends SpaceAggregateImpl implements Updatable {

    private static House instance;
    private final List<Floor> floors = new ArrayList<>();

    public House() {
        collections.put(Floor.class, floors);
    }

    public static synchronized House getInstance() {
        if (instance == null) {
            instance = new House();
        }
        return instance;
    }

    /**
     * Method to report the configuration of the house.
     * @param sb StringBuilder to append the report to
     * @param tab number of tabs to indent the report
     */
    public void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        sb.append("\t".repeat(tab)).append(getClass().getSimpleName()).append("\n");
        floors.forEach(x -> x.buildHouseConfigurationReport(sb, tab + 1));
    }

    @Override
    public void update() {
        floors.forEach(Floor::update);
    }
}
