package cz.cvut.fel.omo.sem.devices.fridge;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * State of the fridge when it is turned off.
 */
class FridgeOFF implements FridgeState {

    private final Fridge fridge;

    FridgeOFF(Fridge fridge) {
        this.fridge = fridge;
    }

    @Override
    public int getElectricityPerUse() {
        return 5;
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
    public void defreezeFreezer() {
        logger.info("Defreezing freezer: " + this.getClass().getSimpleName());
        fridge.doDamage(new Random().nextInt(1, 5));
        fridge.useResources();
        fridge.turnOn();
    }
}
