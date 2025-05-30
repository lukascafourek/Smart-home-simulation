package cz.cvut.fel.omo.sem.actors.pets;

import cz.cvut.fel.omo.sem.actors.Being;
import cz.cvut.fel.omo.sem.house.Room;
import cz.cvut.fel.omo.sem.interfaces.Updatable;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Abstract class representing a pet in the simulation.
 */
public abstract class Pet extends Being implements Updatable {

    private final PetType type;

    public Pet(String name, int age, Room room, PetType type) {
        super(name, age, room);
        this.type = type;
    }

    public PetType getPetType() {
        return type;
    }

    /**
     * Plays with the pet.
     */
    public void play() {
        logger.info("Playing with " + name + " the " + getPetType().toString());
    }

    /**
     * Feeds the pet.
     */
    public void feed() {
        logger.info("Feeding " + name + " the " + getPetType().toString());
    }

    @Override
    public void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        sb.append("\t".repeat(tab)).append(type.toString()).append(" ").append(name)
                .append(": ").append(getAge()).append(" years old\n");
    }

    @Override
    public void update() {
        goToRandomRoom();
    }
}
