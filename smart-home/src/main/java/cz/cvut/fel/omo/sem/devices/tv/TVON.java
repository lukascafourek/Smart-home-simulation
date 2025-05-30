package cz.cvut.fel.omo.sem.devices.tv;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * State of the TV when it is turned on.
 */
class TVON implements TVState {

    private final TV tv;

    TVON(TV tv) {
        this.tv = tv;
    }

    @Override
    public int getElectricityPerUse() {
        return 130;
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
    public void watch() {
        logger.info("Watching TV: " + this.getClass().getSimpleName());
        tv.doDamage(new Random().nextInt(1, 5));
        tv.useResources();
        tv.turnOff();
    }
}
