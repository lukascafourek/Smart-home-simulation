package cz.cvut.fel.omo.sem.devices.pc;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * State of the PC when it is in gaming mode (playing a game).
 */
class PCGaming implements PCState {

    private final PC pc;

    PCGaming(PC pc) {
        this.pc = pc;
    }

    @Override
    public int getElectricityPerUse() {
        return 500;
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
        logger.info("Playing a game on " + this.getClass().getSimpleName());
        pc.doDamage(new Random().nextInt(1, 5));
        pc.useResources();
        if (new Random().nextBoolean()) {
            pc.turnOff();
        } else {
            pc.turnSleep();
        }
    }
}
