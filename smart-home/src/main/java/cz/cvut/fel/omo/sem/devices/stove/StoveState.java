package cz.cvut.fel.omo.sem.devices.stove;

import cz.cvut.fel.omo.sem.devices.DeviceState;

/**
 * Interface for Stove states.
 */
interface StoveState extends DeviceState {

    /**
     * Method for cooking on Stove.
     */
    void cook();
}
