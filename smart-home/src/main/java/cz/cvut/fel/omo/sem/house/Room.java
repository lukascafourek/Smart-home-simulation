package cz.cvut.fel.omo.sem.house;

import cz.cvut.fel.omo.sem.actors.Being;
import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventManager;
import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.interfaces.Updatable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing a room in the house.
 */
public class Room extends SpaceAggregateImpl implements Updatable {

    private final RoomType type;
    private final Floor floor;
    private final List<Being> beings = new ArrayList<>();
    private final List<Device> devices = new ArrayList<>();
    private final List<Window> windows = new ArrayList<>();

    public Room(RoomType type, Floor floor) {
        this.type = type;
        this.floor = floor;
        collections.put(Being.class, beings);
        collections.put(Device.class, devices);
        collections.put(Window.class, windows);
    }

    void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        sb.append("\t".repeat(tab)).append(type.toString()).append("\n");
        beings.forEach(x -> x.buildHouseConfigurationReport(sb, tab + 1));
        devices.forEach(x -> x.buildHouseConfigurationReport(sb, tab + 1));
        windows.forEach(x -> x.buildHouseConfigurationReport(sb, tab + 1));
    }

    public RoomType getRoomType() {
        return this.type;
    }

    public Floor getFloor() {
        return this.floor;
    }

    @Override
    public void update() {
        for (int i = beings.size() - 1; i >= 0; i--) {
            beings.get(i).update();
        }
        devices.forEach(Device::update);
        if (new Random().nextInt(100) == 0 && EventManager.getInstance().eventNotInQueue(EventType.FIRE, this)) {
            devices.forEach(device -> device.doDamage(10));
            EventObservableImpl.getInstance().notifyEvent(new Event<>(EventType.FIRE, this, EventObservableImpl.eventIndex++));
        }
    }
}
