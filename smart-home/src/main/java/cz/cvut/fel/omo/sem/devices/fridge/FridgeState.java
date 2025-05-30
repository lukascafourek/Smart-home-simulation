package cz.cvut.fel.omo.sem.devices.fridge;

import cz.cvut.fel.omo.sem.devices.DeviceState;

/**
 * Interface for Fridge states
 */
interface FridgeState extends DeviceState {

    /**
     * Method to defreeze the freezer
     */
    void defreezeFreezer();
}
