package cz.cvut.fel.omo.sem.devices.sensor;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.house.Room;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Sensor class represents a Sensor device in the house.
 */
public class Sensor extends Device {

    private int windSpeed = 20;
    private final int windLimit = 5;
    private int temperature = 20;
    private final int tempLimit = 5;

    public Sensor(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.CALLSERVICE;
        electricityPerUse = 1;
    }

    @Override
    public void use() {
        logger.info("Checking wind speed: " + windSpeed);
    }

    private void detectWind() {
        EventObservableImpl.getInstance().notifyEvent(new Event<>(EventType.WIND, this, EventObservableImpl.eventIndex++));
    }

    private void detectTemperature() {
        EventObservableImpl.getInstance().notifyEvent(new Event<>(EventType.TEMP_CHANGE, this, EventObservableImpl.eventIndex++));
    }

    @Override
    public void update() {
        super.update();
        int windTmp = new Random().nextInt(36);
        int tempTmp = new Random().nextInt(36);
        if ((temperature <= tempLimit && tempTmp > tempLimit) || (temperature > tempLimit && tempTmp <= tempLimit)) {
            detectTemperature();
        }
        temperature = tempTmp;
        if (temperature > tempLimit) {
            if ((windTmp <= windLimit && windSpeed > windLimit) || (windTmp > windLimit && windSpeed <= windLimit)) {
                detectWind();
            }
        }
        windSpeed = windTmp;
    }
}
