package cz.cvut.fel.omo.sem.devices.pc;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.house.Room;

import java.util.Random;

/**
 * PC class represents a PC device in the house.
 */
public class PC extends Device {

    private PCState pcState;

    public PC(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.SELFREPAIR;
        setPcState(new PCOFF(this));
    }

    @Override
    public void use() {
        int random = new Random().nextInt(4);
        switch (random) {
            case 0 -> turnSleep();
            case 1 -> turnOff();
            case 2 -> internetSearching();
            case 3 -> game();
        }
    }

    void turnSleep() {
        setPcState(new PCSleep(this));
    }

    void turnOff() {
        setPcState(new PCOFF(this));
    }

    void turnBasic() {
        setPcState(new PCBasic(this));
    }

    void turnGaming() {
        setPcState(new PCGaming(this));
    }

    void internetSearching() {
        pcState.internetSearching();
    }

    void game() {
        pcState.game();
    }

    private void setPcState(PCState pcState) {
        this.pcState = pcState;
        electricityPerUse = pcState.getElectricityPerUse();
        waterPerUse = pcState.getWaterPerUse();
        gasPerUse = pcState.getGasPerUse();
    }
}
