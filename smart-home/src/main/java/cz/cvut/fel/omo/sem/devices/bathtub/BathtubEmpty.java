package cz.cvut.fel.omo.sem.devices.bathtub;

/**
 * Bathtub state when it is empty.
 */
class BathtubEmpty implements BathtubState {

    private final Bathtub bathtub;

    BathtubEmpty(Bathtub bathtub) {
        this.bathtub = bathtub;
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
    public void bath() {
        bathtub.fill();
        bathtub.use();
    }
}
