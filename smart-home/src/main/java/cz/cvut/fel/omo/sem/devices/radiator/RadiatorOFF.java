package cz.cvut.fel.omo.sem.devices.radiator;

/**
 * State of the Radiator when it is turned off.
 */
class RadiatorOFF implements RadiatorState {

    private Radiator radiator;

    RadiatorOFF(Radiator radiator) {
        this.radiator = radiator;
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
}
