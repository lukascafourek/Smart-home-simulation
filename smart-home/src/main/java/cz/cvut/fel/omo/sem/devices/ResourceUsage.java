package cz.cvut.fel.omo.sem.devices;

/**
 * Class representing resource usage of a device.
 */
public class ResourceUsage {

    private int electricity;
    private int water;
    private int gas;

    void addElectricity(int amount) {
        electricity += amount;
    }

    void addWater(int amount) {
        water += amount;
    }

    void addGas(int amount) {
        gas += amount;
    }

    public int getElectricity() {
        return electricity;
    }

    public int getWater() {
        return water;
    }

    public int getGas() {
        return gas;
    }
}
