package cz.cvut.fel.omo.sem.devices.pc;

import cz.cvut.fel.omo.sem.devices.DeviceState;

/**
 * Interface for PC states.
 */
interface PCState extends DeviceState {

    /**
     * Method for internet searching on PC.
     */
    void internetSearching();

    /**
     * Method for gaming on PC.
     */
    void game();
}
