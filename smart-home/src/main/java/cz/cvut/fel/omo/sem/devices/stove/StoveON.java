package cz.cvut.fel.omo.sem.devices.stove;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * State of the Stove when it is turned on.
 */
class StoveON implements StoveState {

    private final Stove stove;

    StoveON(Stove stove) {
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
        return 500;
    }

    @Override
    public void cook() {
        logger.info("Cooking on stove: " + this.getClass().getSimpleName());
        stove.doDamage(new Random().nextInt(1, 5));
        stove.useResources();
        stove.turnOff();
    }
}
