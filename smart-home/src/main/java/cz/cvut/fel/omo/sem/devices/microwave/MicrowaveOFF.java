package cz.cvut.fel.omo.sem.devices.microwave;

/**
 * State of the microwave when it is turned off.
 */
class MicrowaveOFF implements MicrowaveState {

    private final Microwave microwave;

    MicrowaveOFF(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public int getElectricityPerUse() {
        return 1;
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
    public void heat() {
        microwave.turnON();
        microwave.use();
    }
}
