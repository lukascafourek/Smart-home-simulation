package cz.cvut.fel.omo.sem.devices.tv;

import cz.cvut.fel.omo.sem.devices.DeviceState;

/**
 * Interface for TV states.
 */
interface TVState extends DeviceState {

    /**
     * Method for watching TV.
     */
    void watch();
}
