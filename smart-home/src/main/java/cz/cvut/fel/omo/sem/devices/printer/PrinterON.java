package cz.cvut.fel.omo.sem.devices.printer;

import java.util.Random;

/**
 * State of the Printer when it is turned on.
 */
class PrinterON implements PrinterState {

    private final Printer printer;

    PrinterON(Printer printer) {
        this.printer = printer;
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
    public void print() {
        printer.useInk();
        printer.doDamage(new Random().nextInt(1, 5));
        printer.useResources();
        if (new Random().nextBoolean()) {
            printer.turnOff();
        } else {
            printer.turnIdle();
        }
    }

    @Override
    public void notifyToRefill() {
        printer.turnOff();
        printer.notifyToRefill();
    }
}
