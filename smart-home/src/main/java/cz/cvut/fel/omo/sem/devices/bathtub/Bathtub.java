package cz.cvut.fel.omo.sem.devices.bathtub;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.house.Room;

/**
 * Bathtub class represents a bathtub device in the house.
 */
public class Bathtub extends Device {

    private BathtubState bathtubState;

    public Bathtub(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.CALLSERVICE;
        setBathtubState(new BathtubEmpty(this));
    }

    @Override
    public void use() {
        bathtubState.bath();
    }

    void fill() {
        setBathtubState(new BathtubFilled(this));
    }

    void empty() {
        setBathtubState(new BathtubEmpty(this));
    }

    private void setBathtubState(BathtubState state) {
        this.bathtubState = state;
        electricityPerUse = state.getElectricityPerUse();
        waterPerUse = state.getWaterPerUse();
        gasPerUse = state.getGasPerUse();
    }
}
