package cz.cvut.fel.omo.sem.devices;

import cz.cvut.fel.omo.sem.devices.bathtub.Bathtub;
import cz.cvut.fel.omo.sem.devices.fridge.Fridge;
import cz.cvut.fel.omo.sem.devices.microwave.Microwave;
import cz.cvut.fel.omo.sem.devices.pc.PC;
import cz.cvut.fel.omo.sem.devices.printer.Printer;
import cz.cvut.fel.omo.sem.devices.radiator.Radiator;
import cz.cvut.fel.omo.sem.devices.sensor.Sensor;
import cz.cvut.fel.omo.sem.devices.stove.Stove;
import cz.cvut.fel.omo.sem.devices.tv.TV;
import cz.cvut.fel.omo.sem.devices.wc.WC;
import cz.cvut.fel.omo.sem.house.Room;

/**
 * Factory class for creating types of devices.
 */
public class DeviceFactory {

    /**
     * Creates a device based on the device type.
     * @param deviceType type of device
     * @param room room where the device is located
     * @return created device
     */
    public static Device create(DeviceType deviceType, Room room) {
        return switch (deviceType) {
            case TV -> new TV(deviceType, room);
            case FRIDGE -> new Fridge(deviceType, room);
            case SENSOR -> new Sensor(deviceType, room);
            case PC -> new PC(deviceType, room);
            case PRINTER -> new Printer(deviceType, room);
            case WC -> new WC(deviceType, room);
            case BATHTUB -> new Bathtub(deviceType, room);
            case STOVE -> new Stove(deviceType, room);
            case MICROWAVE -> new Microwave(deviceType, room);
            case RADIATOR -> new Radiator(deviceType, room);
        };
    }
}
