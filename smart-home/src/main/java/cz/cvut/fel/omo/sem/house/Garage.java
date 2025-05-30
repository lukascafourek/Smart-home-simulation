package cz.cvut.fel.omo.sem.house;

import cz.cvut.fel.omo.sem.devices.outside.OutsideGear;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a garage in a house storing outside gear.
 */
public class Garage extends Room {

    private final List<OutsideGear> outsideGears = new ArrayList<>();

    public Garage(RoomType type, Floor floor) {
        super(type, floor);
        collections.put(OutsideGear.class, outsideGears);
    }

    @Override
    void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        super.buildHouseConfigurationReport(sb, tab);
        outsideGears.forEach(x -> x.buildHouseConfigurationReport(sb, tab + 1));
    }

    @Override
    public void update() {
        super.update();
        outsideGears.forEach(OutsideGear::update);
    }
}
