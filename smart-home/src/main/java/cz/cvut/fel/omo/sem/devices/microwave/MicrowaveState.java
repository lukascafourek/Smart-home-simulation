package cz.cvut.fel.omo.sem.devices.microwave;

import cz.cvut.fel.omo.sem.devices.DeviceState;

/**
 * Interface for Microwave states
 */
interface MicrowaveState extends DeviceState {

    /**
     * Method to start heating food.
     */
    void heat();
}
