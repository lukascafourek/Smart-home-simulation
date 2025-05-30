package cz.cvut.fel.omo.sem.devices.stove;

/**
 * State of the Stove when it is turned off.
 */
class StoveOFF implements StoveState {

    private final Stove stove;

    StoveOFF(Stove stove) {
        this.stove = stove;
    }

    @Override
    public int getElectricityPerUse() {
        return 0;
    }

    @Override
    public int getWaterPerUse() {
        return 0;
    }

    @Override
    public int getGasPerUse() {
        return 0;
    }

    @Override
    public void cook() {
        stove.turnOn();
        stove.use();
    }
}
