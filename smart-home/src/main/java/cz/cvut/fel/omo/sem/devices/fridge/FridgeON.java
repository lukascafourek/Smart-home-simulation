package cz.cvut.fel.omo.sem.devices.fridge;

/**
 * State of the fridge when it is turned on.
 */
class FridgeON implements FridgeState {

    private final Fridge fridge;

    FridgeON(Fridge fridge) {
        this.fridge = fridge;
    }

    @Override
    public int getElectricityPerUse() {
        return 100;
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
        fridge.turnOff();
        fridge.defreezeFreezer();
    }
}
