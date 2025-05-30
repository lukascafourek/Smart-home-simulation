package cz.cvut.fel.omo.sem.actors.pets;

import cz.cvut.fel.omo.sem.house.Room;

/**
 * Hamster class represents a Hamster pet.
 */
public class Hamster extends Pet {

    public Hamster(String name, int age, Room room, PetType type) {
        super(name, age, room, type);
    }
}
