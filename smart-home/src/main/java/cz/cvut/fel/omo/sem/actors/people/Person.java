package cz.cvut.fel.omo.sem.actors.people;

import cz.cvut.fel.omo.sem.actors.Being;
import cz.cvut.fel.omo.sem.actors.activities.*;
import cz.cvut.fel.omo.sem.actors.pets.Pet;
import cz.cvut.fel.omo.sem.devices.fridge.Fridge;
import cz.cvut.fel.omo.sem.devices.outside.OutsideGear;
import cz.cvut.fel.omo.sem.devices.printer.Printer;
import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventManager;
import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.house.Floor;
import cz.cvut.fel.omo.sem.house.Room;
import cz.cvut.fel.omo.sem.house.RoomType;
import cz.cvut.fel.omo.sem.interfaces.EventObserver;
import cz.cvut.fel.omo.sem.devices.Device;

import java.util.*;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Abstract class representing a person in the simulation.
 */
public abstract class Person extends Being implements EventObserver {

    private final PersonType type;
    private PersonState state = PersonState.INSIDE;
    private final Map<Device, Integer> deviceUsage = new HashMap<>();
    private final Map<OutsideGear, Integer> outsideGearUsage = new HashMap<>();
    private final Map<Pet, Integer> petUsage = new HashMap<>();
    private final ActivityManager activityManager = new ActivityManager(this);

    public Person(String name, int age, Room room, PersonType type) {
        super(name, age, room);
        this.type = type;
    }

    /**
     * Stops the activity of the person.
     */
    public void stopActivity() {
        activityManager.stopActivity();
    }

    private void handleEvent(Event<?> event) {
        logger.entering(Person.class.getSimpleName(), "handleEvent", event);
        if (event == null) {
            logger.exiting(Person.class.getSimpleName(), "handleEvent");
            return;
        }
        switch (event.getEventType()) {
            case BREAK -> repairDevice((Device) event.getEventSrc());
            case EMPTY_FRIDGE -> refillFridge((Fridge) event.getEventSrc());
            case FIRE -> douseFire((Room) event.getEventSrc());
            case CRYING_BABY -> soothe((Baby) event.getEventSrc());
            case REFILL_INK -> refillPrinterInk((Printer) event.getEventSrc());
        }
        logger.exiting(Person.class.getSimpleName(), "handleEvent");
    }

    private void soothe(Baby baby) {
        logger.info("Soothing baby: " + baby.getName());
        if (new Random().nextBoolean()) {
            baby.goToRandomRoom();
        }
    }

    private void douseFire(Room room) {
        logger.info("Dousing fire in room: " + room.getRoomType().toString());
    }

    private void refillFridge(Device device) {
        ((Fridge) device).refillFridge();
    }

    private void refillPrinterInk(Device device) {
        ((Printer) device).refillInk();
    }

    private void repairDevice(Device eventSrc) {
        eventSrc.repair();
    }

    private void processEventQueue() {
        while (!EventManager.getInstance().isQueueEmpty()) {
            Event<?> event = EventManager.getInstance().getNextEvent();
            handleEvent(event);
            EventObservableImpl.getInstance().personHandlesEventQueue(event, this);
        }
    }

    @Override
    public void updateEvent(Event<?> event) {
        handleEvent(event);
    }

    @Override
    public void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        sb.append("\t".repeat(tab)).append(type.toString()).append(" ").append(name)
                .append(": ").append(getAge()).append(" years old\n");
    }

    public Map<Device, Integer> getDeviceUsage() {
        return deviceUsage;
    }

    public Map<OutsideGear, Integer> getOutsideGearUsage() {
        return outsideGearUsage;
    }

    public Map<Pet, Integer> getPetUsage() {
        return petUsage;
    }

    public PersonType getPersonType() {
        return type;
    }

    public PersonState getPersonState() {
        return state;
    }

    public void setState(PersonState state) {
        this.state = state;
    }

    private void goToGarage() {
        logger.entering(Person.class.getSimpleName(), "goToGarage");
        List<Room> rooms = room.getFloor().getHouse().getItems(Floor.class).stream()
                .flatMap(floor -> floor.getItems(Room.class).stream())
                .filter(room -> room.getRoomType().equals(RoomType.GARAGE)).toList();
        if (!rooms.isEmpty()) {
            setRoom(rooms.get(new Random().nextInt(rooms.size())));
            activityManager.selectRandomOutsideGear();
        } else {
            update();
        }
        logger.exiting(Person.class.getSimpleName(), "goToGarage");
    }

    @Override
    public void update() {
        stopActivity();
        processEventQueue();
        boolean whereToGo = new Random().nextBoolean(); // true - stay inside, false - go to garage
        if (whereToGo) {
            goToRandomRoom();
            ActivityType activityType = ActivityType.values()[new Random().nextInt(ActivityType.values().length - 1)];
            switch (activityType) {
                case PLAYING, FEEDING -> activityManager.selectRandomPet(activityType);
                case USING, CLEANING -> activityManager.selectRandomDevice(activityType);
            }
        } else {
            goToGarage();
        }
    }
}
