package cz.cvut.fel.omo.sem.devices.bathtub;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Bathtub state when it is filled with water.
 */
class BathtubFilled implements BathtubState {

    private final Bathtub bathtub;

    BathtubFilled(Bathtub bathtub) {
        this.bathtub = bathtub;
    }

    @Override
    public int getElectricityPerUse() {
        return 0;
    }

    @Override
    public int getWaterPerUse() {
        return 50;
    }

    @Override
    public int getGasPerUse() {
        return 0;
    }

    @Override
    public void bath() {
        logger.info("Using bathtub: " + this.getClass().getSimpleName());
        bathtub.doDamage(new Random().nextInt(1, 5));
        bathtub.useResources();
        bathtub.empty();
    }
}
