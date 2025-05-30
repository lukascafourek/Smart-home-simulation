package cz.cvut.fel.omo.sem.devices.wc;

/**
 * State of the WC before flushing.
 */
class WCBeforeFlush implements WCState {

    private final WC wc;

    WCBeforeFlush(WC wc) {
        this.wc = wc;
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
    public void flush() {
        wc.flushAfterUse();
        wc.use();
    }
}
