package cz.cvut.fel.omo.sem.devices.printer;

import cz.cvut.fel.omo.sem.devices.DeviceState;

/**
 * Interface for Printer states.
 */
interface PrinterState extends DeviceState {

    /**
     * Method for printing on Printer.
     */
    void print();

    /**
     * Method for notifying to refill ink.
     */
    void notifyToRefill();
}
