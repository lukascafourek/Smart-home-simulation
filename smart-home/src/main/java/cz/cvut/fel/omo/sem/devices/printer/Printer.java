package cz.cvut.fel.omo.sem.devices.printer;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.events.EventManager;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.house.Room;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Printer class represents a Printer device in the house.
 */
public class Printer extends Device {

    private PrinterState printerState;
    private int inkLevel;
    private final int maxInkLevel = 5;

    public Printer(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.SELFREPAIR;
        inkLevel = maxInkLevel;
        setPrinterState(new PrinterOFF(this));
    }

    @Override
    public void update() {
        super.update();
        if (inkLevel <= 0 && EventManager.getInstance().eventNotInQueue(EventType.REFILL_INK, this)) {
            notifyToRefill();
        }
    }

    @Override
    public void use() {
        int random = new Random().nextInt(3);
        switch (random) {
            case 0 -> turnOff();
            case 1 -> turnIdle();
            case 2 -> print();
        }
    }

    void turnOn() {
        setPrinterState(new PrinterON(this));
    }

    void turnOff() {
        setPrinterState(new PrinterOFF(this));
    }

    void turnIdle() {
        setPrinterState(new PrinterIdle(this));
    }

    void print() {
        printerState.print();
    }

    void notifyToRefill() {
        printerState.notifyToRefill();
    }

    void useInk() {
        logger.info("Using ink: " + this.getClass().getSimpleName());
        if (inkLevel > 0) {
            inkLevel = inkLevel - new Random().nextInt(1, inkLevel + 1);
        }
    }

    /**
     * Refills ink to max level by a person.
     */
    public void refillInk() {
        logger.info("Refilling ink to max: " + this.getClass().getSimpleName());
        inkLevel = maxInkLevel;
    }

    private void setPrinterState(PrinterState state) {
        this.printerState = state;
        electricityPerUse = state.getElectricityPerUse();
        waterPerUse = state.getWaterPerUse();
        gasPerUse = state.getGasPerUse();
    }

    public int getInkLevel() {
        return inkLevel;
    }

    public void setInkLevel(int inkLevel) {
        this.inkLevel = inkLevel;
    }
}
