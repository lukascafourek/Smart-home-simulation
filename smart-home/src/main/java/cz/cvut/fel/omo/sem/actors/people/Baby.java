package cz.cvut.fel.omo.sem.actors.people;

import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventManager;
import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.house.Room;

import java.util.Random;

/**
 * Baby class represents a baby person in the simulation.
 */
public class Baby extends Person {

    public Baby(String name, int age, Room room, PersonType type) {
        super(name, age, room, type);
    }

    @Override
    public void update() {
        if ((new Random().nextInt(25)) == 0 && EventManager.getInstance().eventNotInQueue(EventType.CRYING_BABY, this)) {
            EventObservableImpl.getInstance().notifyEvent(new Event<>(EventType.CRYING_BABY, this, EventObservableImpl.eventIndex++));
        }
    }
}
