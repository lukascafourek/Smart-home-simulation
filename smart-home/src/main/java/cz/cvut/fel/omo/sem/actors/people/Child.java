package cz.cvut.fel.omo.sem.actors.people;

import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.house.Room;
import cz.cvut.fel.omo.sem.reports.ActivityAndUsageReport;

/**
 * Child class represents a child person in the simulation.
 */
public class Child extends Person {

    public Child(String name, int age, Room room, PersonType type) {
        super(name, age, room, type);
        ActivityAndUsageReport.getInstance().addPerson(this);
        EventObservableImpl.getInstance().addObserver(EventType.CRYING_BABY, this);
    }
}
