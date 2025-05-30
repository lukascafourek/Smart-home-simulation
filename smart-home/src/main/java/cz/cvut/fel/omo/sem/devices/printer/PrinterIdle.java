package cz.cvut.fel.omo.sem.devices.printer;

/**
 * State of the Printer when it is idle.
 */
class PrinterIdle implements PrinterState {

    private final Printer printer;

    PrinterIdle(Printer printer) {
        this.printer = printer;
    }

    @Override
    public int getElectricityPerUse() {
        return 3;
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
        printer.turnOn();
        printer.print();
    }

    @Override
    public void notifyToRefill() {
        printer.turnOff();
        printer.notifyToRefill();
    }
}
