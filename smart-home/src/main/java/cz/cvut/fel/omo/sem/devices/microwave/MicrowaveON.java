package cz.cvut.fel.omo.sem.devices.microwave;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Represents the state of the microwave when it is turned on.
 */
class MicrowaveON implements MicrowaveState {

    private final Microwave microwave;

    MicrowaveON(Microwave microwave) {
        this.microwave = microwave;
    }

    @Override
    public int getElectricityPerUse() {
        return 250;
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
        logger.info("Cooking in microwave: " + this.getClass().getSimpleName());
        microwave.doDamage(new Random().nextInt(1, 5));
        microwave.useResources();
        microwave.turnOFF();
    }
}
