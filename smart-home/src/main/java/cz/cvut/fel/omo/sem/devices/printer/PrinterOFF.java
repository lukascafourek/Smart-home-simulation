package cz.cvut.fel.omo.sem.devices.printer;

import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;

/**
 * State of the Printer when it is turned off.
 */
class PrinterOFF implements PrinterState {

    private final Printer printer;

    PrinterOFF(Printer printer) {
        this.printer = printer;
    }

    @Override
    public int getElectricityPerUse() {
        return 1;
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
        EventObservableImpl.getInstance().notifyEvent(new Event<>(EventType.REFILL_INK, printer, EventObservableImpl.eventIndex++));
    }
}
