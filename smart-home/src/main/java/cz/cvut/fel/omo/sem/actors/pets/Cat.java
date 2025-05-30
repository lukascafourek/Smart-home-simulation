package cz.cvut.fel.omo.sem.actors.pets;

import cz.cvut.fel.omo.sem.house.Room;

/**
 * Cat class representing a cat pet.
 */
public class Cat extends Pet {

    public Cat(String name, int age, Room room, PetType type) {
        super(name, age, room, type);
    }
}
