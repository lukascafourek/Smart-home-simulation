package cz.cvut.fel.omo.sem.devices.microwave;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.house.Room;

/**
 * Microwave class represents a microwave device in the house.
 */
public class Microwave extends Device {

    private MicrowaveState microwaveState;

    public Microwave(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.NEWPRODUCT;
        setMicrowaveState(new MicrowaveOFF(this));
    }

    @Override
    public void use() {
        microwaveState.heat();
    }

    void turnON() {
        setMicrowaveState(new MicrowaveON(this));
    }

    void turnOFF() {
        setMicrowaveState(new MicrowaveOFF(this));
    }

    private void setMicrowaveState(MicrowaveState microwaveState) {
        this.microwaveState = microwaveState;
        electricityPerUse = microwaveState.getElectricityPerUse();
        waterPerUse = microwaveState.getWaterPerUse();
        gasPerUse = microwaveState.getGasPerUse();
    }
}
