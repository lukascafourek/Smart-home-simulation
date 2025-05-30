package cz.cvut.fel.omo.sem.devices;

/**
 * Interface for device states
 */
public interface DeviceState {

    /**
     * Getter for electricity consumption per use
     */
    int getElectricityPerUse();

    /**
     * Getter for water consumption per use
     */
    int getWaterPerUse();

    /**
     * Getter for gas consumption per use
     */
    int getGasPerUse();
}
