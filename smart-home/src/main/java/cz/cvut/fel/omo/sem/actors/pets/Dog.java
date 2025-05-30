package cz.cvut.fel.omo.sem.actors.pets;

import cz.cvut.fel.omo.sem.house.Room;

/**
 * Dog class represents a dog pet.
 */
public class Dog extends Pet {

    public Dog(String name, int age, Room room, PetType type) {
        super(name, age, room, type);
    }
}
