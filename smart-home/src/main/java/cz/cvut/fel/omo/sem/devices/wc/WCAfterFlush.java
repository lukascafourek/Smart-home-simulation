package cz.cvut.fel.omo.sem.devices.wc;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * State of the WC after it has been flushed.
 */
class WCAfterFlush implements WCState {

    private final WC wc;

    WCAfterFlush(WC wc) {
        this.wc = wc;
    }

    @Override
    public int getElectricityPerUse() {
        return 0;
    }

    @Override
    public int getWaterPerUse() {
        return 10;
    }

    @Override
    public int getGasPerUse() {
        return 0;
    }

    @Override
    public void flush() {
        logger.info("Flushing WC: " + this.getClass().getSimpleName());
        wc.doDamage(new Random().nextInt(1, 5));
        wc.useResources();
        wc.fillWater();
    }
}
