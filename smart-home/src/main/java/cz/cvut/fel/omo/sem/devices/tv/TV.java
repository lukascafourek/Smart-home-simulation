package cz.cvut.fel.omo.sem.devices.tv;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.house.Room;

import java.util.Random;

/**
 * TV class represents a TV device in the house.
 */
public class TV extends Device {

    private TVState tvState;

    public TV(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.NEWPRODUCT;
        setTvState(new TVOFF(this));
    }

    @Override
    public void use() {
        int random = new Random().nextInt(3);
        switch (random) {
            case 0 -> turnOn();
            case 1 -> turnOff();
            case 2 -> watch();
        }
    }

    void turnOn() {
        setTvState(new TVON(this));
    }

    void turnOff() {
        setTvState(new TVOFF(this));
    }

    void watch() {
        tvState.watch();
    }

    private void setTvState(TVState tvState) {
        this.tvState = tvState;
        electricityPerUse = tvState.getElectricityPerUse();
        waterPerUse = tvState.getWaterPerUse();
        gasPerUse = tvState.getGasPerUse();
    }
}
