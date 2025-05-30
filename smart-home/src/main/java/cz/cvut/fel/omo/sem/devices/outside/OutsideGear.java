package cz.cvut.fel.omo.sem.devices.outside;

import cz.cvut.fel.omo.sem.actors.Being;
import cz.cvut.fel.omo.sem.actors.people.Person;
import cz.cvut.fel.omo.sem.actors.people.PersonState;
import cz.cvut.fel.omo.sem.house.Room;
import cz.cvut.fel.omo.sem.interfaces.Updatable;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Represents an outside gear that can be used by a person.
 */
public class OutsideGear implements Updatable {

    private final Room room;
    boolean availability = true;
    private final OutsideGearType type;
    private int usage = 0;
    private Person person;

    public OutsideGear(OutsideGearType type, Room room) {
        this.room = room;
        this.type = type;
    }

    /**
     * Person goes outside with the gear.
     * @param person person that goes outside
     */
    public void goOutside(Person person) {
        logger.info(person.getName() + " is outside with " + type.toString());
        this.person = person;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setUnavailable() {
        availability = false;
    }

    /**
     * Person stops using the gear.
     */
    public void stopUsing() {
        availability = true;
        usage = 0;
        person.setState(PersonState.INSIDE);
        room.addItem(Being.class, person);
        person = null;
    }

    /**
     * Method to build a report about the outside gear.
     * @param sb string builder to append the report to
     * @param tab number of tabs to indent the report
     */
    public void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        sb.append("\t".repeat(tab)).append(type.toString());
        if (person != null) {
            sb.append(" (currently used by ").append(person.getPersonType().toString())
                    .append(" ").append(person.getName()).append(": ")
                    .append(person.getAge()).append(" years old)");
        }
        sb.append("\n");
    }

    public Room getRoom() {
        return room;
    }

    public OutsideGearType getType() {
        return type;
    }

    @Override
    public void update() {
        if (!availability) {
            usage++;
            if (usage > 5) {
                person.stopActivity();
            }
        }
    }

    public Person getPerson() {
        return person;
    }
}
