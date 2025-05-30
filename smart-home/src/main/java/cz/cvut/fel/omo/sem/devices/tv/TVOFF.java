package cz.cvut.fel.omo.sem.devices.tv;

/**
 * State of the TV when it is turned off.
 */
class TVOFF implements TVState {

    private final TV tv;

    TVOFF(TV tv) {
        this.tv = tv;
    }

    @Override
    public int getElectricityPerUse() {
        return 2;
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
        tv.turnOn();
        tv.watch();
    }
}
