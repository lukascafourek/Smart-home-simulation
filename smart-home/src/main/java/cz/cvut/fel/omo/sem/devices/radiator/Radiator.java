package cz.cvut.fel.omo.sem.devices.radiator;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.house.Room;
import cz.cvut.fel.omo.sem.interfaces.EventObserver;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Radiator class represents a Radiator device in the house.
 */
public class Radiator extends Device implements EventObserver {

    private RadiatorState radiatorState;

    public Radiator(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.CALLSERVICE;
        setRadiatorState(new RadiatorOFF(this));
        EventObservableImpl.getInstance().addObserver(EventType.TEMP_CHANGE, this);
    }

    @Override
    public void use() {
        logger.info("Checking radiator state: " + radiatorState.getClass().getSimpleName());
    }

    @Override
    public void updateEvent(Event<?> event) {
        if (event.getEventType() == EventType.TEMP_CHANGE) {
            logger.info("Radiator is turning on/off");
            if (radiatorState instanceof RadiatorOFF) {
                turnOn();
            } else {
                turnOff();
            }
        }
    }

    private void turnOn() {
        setRadiatorState(new RadiatorON(this));
    }

    private void turnOff() {
        setRadiatorState(new RadiatorOFF(this));
    }

    private void setRadiatorState(RadiatorState radiatorState) {
        this.radiatorState = radiatorState;
        electricityPerUse = radiatorState.getElectricityPerUse();
        waterPerUse = radiatorState.getWaterPerUse();
        gasPerUse = radiatorState.getGasPerUse();
    }
}
