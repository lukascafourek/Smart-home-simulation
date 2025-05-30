package cz.cvut.fel.omo.sem.devices.fridge;

import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventManager;
import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.house.Room;

import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Fridge class represents a fridge device in the house.
 */
public class Fridge extends Device {

    private FridgeState fridgeState;
    private int rations;
    private final int maxRations = 5;

    public Fridge(DeviceType type, Room room) {
        super(type, room);
        repairManual = Repair.CALLSERVICE;
        rations = maxRations;
        setFridgeState(new FridgeON(this));
    }

    @Override
    public void update() {
        super.update();
        if (rations <= 0 && EventManager.getInstance().eventNotInQueue(EventType.EMPTY_FRIDGE, this)) {
            EventObservableImpl.getInstance().notifyEvent(new Event<>(EventType.EMPTY_FRIDGE, this, EventObservableImpl.eventIndex++));
        }
    }

    @Override
    public void use() {
        int random = new Random().nextInt(2);
        switch (random) {
            case 0 -> getFood();
            case 1 -> defreezeFreezer();
        }
    }

    void turnOn() {
        setFridgeState(new FridgeON(this));
    }

    void turnOff() {
        setFridgeState(new FridgeOFF(this));
    }

    private void getFood() {
        logger.info("Getting food from fridge: " + this.getClass().getSimpleName());
        if (rations > 0) {
            rations = rations - new Random().nextInt(1, rations + 1);
        }
    }

    void defreezeFreezer() {
        fridgeState.defreezeFreezer();
    }

    private void setFridgeState(FridgeState fridgeState) {
        this.fridgeState = fridgeState;
        electricityPerUse = fridgeState.getElectricityPerUse();
        waterPerUse = fridgeState.getWaterPerUse();
        gasPerUse = fridgeState.getGasPerUse();
    }

    /**
     * Refills the fridge with rations.
     */
    public void refillFridge() {
        logger.info("Refilling fridge: " + this.getClass().getSimpleName());
        rations = maxRations;
    }

    public int getRations() {
        return rations;
    }

    public void setRations(int rations) {
        this.rations = rations;
    }
}
