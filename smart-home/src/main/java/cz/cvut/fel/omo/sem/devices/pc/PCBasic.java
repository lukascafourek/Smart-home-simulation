package cz.cvut.fel.omo.sem.devices.pc;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * State of the PC when it is in basic mode (searching on the internet).
 */
class PCBasic implements PCState {

    private final PC pc;

    PCBasic(PC pc) {
        this.pc = pc;
    }

    @Override
    public int getElectricityPerUse() {
        return 50;
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
        logger.info("Searching the internet on " + this.getClass().getSimpleName());
        pc.doDamage(new Random().nextInt(1, 5));
        pc.useResources();
        if (new Random().nextBoolean()) {
            pc.turnOff();
        } else {
            pc.turnSleep();
        }
    }

    @Override
    public void game() {
        pc.turnGaming();
        pc.game();
    }
}
