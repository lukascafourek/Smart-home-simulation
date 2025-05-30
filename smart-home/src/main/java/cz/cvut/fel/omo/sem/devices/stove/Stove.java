package cz.cvut.fel.omo.sem.devices.stove;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.house.Room;

/**
 * Stove class represents a Stove device in the house.
 */
public class Stove extends Device {

    private StoveState stoveState;

    public Stove(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.CALLSERVICE;
        setStoveState(new StoveOFF(this));
    }

    @Override
    public void use() {
        stoveState.cook();
    }

    void turnOn() {
        setStoveState(new StoveON(this));
    }

    void turnOff() {
        setStoveState(new StoveOFF(this));
    }

    private void setStoveState(StoveState stoveState) {
        this.stoveState = stoveState;
        electricityPerUse = stoveState.getElectricityPerUse();
        waterPerUse = stoveState.getWaterPerUse();
        gasPerUse = stoveState.getGasPerUse();
    }
}
