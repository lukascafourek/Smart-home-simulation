package cz.cvut.fel.omo.sem.devices.bathtub;

import cz.cvut.fel.omo.sem.devices.DeviceState;

/**
 * Interface for Bathtub states
 */
interface BathtubState extends DeviceState {

    /**
     * Method for using the bathtub by person
     */
    void bath();
}
