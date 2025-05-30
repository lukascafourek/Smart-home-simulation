package cz.cvut.fel.omo.sem.devices.pc;

/**
 * State of the PC when it is turned off.
 */
class PCOFF implements PCState {

    private final PC pc;

    PCOFF(PC pc) {
        this.pc = pc;
    }

    @Override
    public int getElectricityPerUse() {
        return 10;
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
    public void internetSearching() {
        pc.turnBasic();
        pc.internetSearching();
    }

    @Override
    public void game() {
        pc.turnGaming();
        pc.game();
    }
}
