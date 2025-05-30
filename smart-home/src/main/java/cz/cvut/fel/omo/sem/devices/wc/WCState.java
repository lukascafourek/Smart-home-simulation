package cz.cvut.fel.omo.sem.devices.wc;

import cz.cvut.fel.omo.sem.devices.DeviceState;

/**
 * Interface for WC states.
 */
interface WCState extends DeviceState {

    /**
     * Method for flushing the WC.
     */
    void flush();
}
