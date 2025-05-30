package cz.cvut.fel.omo.sem.devices.wc;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.house.Room;

/**
 * WC class represents a WC device in the house.
 */
public class WC extends Device {

    private WCState wcState;

    public WC(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.CALLSERVICE;
        setWCState(new WCBeforeFlush(this));
    }

    @Override
    public void use() {
        wcState.flush();
    }

    void flushAfterUse() {
        setWCState(new WCAfterFlush(this));
    }

    void fillWater() {
        setWCState(new WCBeforeFlush(this));
    }

    private void setWCState(WCState state) {
        this.wcState = state;
        electricityPerUse = wcState.getElectricityPerUse();
        waterPerUse = wcState.getWaterPerUse();
        gasPerUse = wcState.getGasPerUse();
    }
}
