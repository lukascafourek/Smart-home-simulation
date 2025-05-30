package cz.cvut.fel.omo.sem.actors.pets;

import cz.cvut.fel.omo.sem.house.Room;

/**
 * Parrot class represents a parrot pet.
 */
public class Parrot extends Pet{

    public Parrot(String name, int age, Room room, PetType type) {
        super(name, age, room, type);
    }
}
