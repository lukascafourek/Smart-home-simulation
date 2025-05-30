package cz.cvut.fel.omo.sem.actors.pets;

import cz.cvut.fel.omo.sem.house.Room;

/**
 * Factory class for creating types of pets
 */
public class PetFactory {

    /**
     * Creates a pet of a given type
     * @param petType type of pet
     * @param age age of pet
     * @param petName name of pet
     * @param room room where the pet is located
     * @return created pet
     */
    public static Pet create(PetType petType, int age, String petName, Room room) {
        return switch (petType) {
            case CAT -> new Cat(petName, age, room, petType);
            case DOG -> new Dog(petName, age, room, petType);
            case HAMSTER -> new Hamster(petName, age, room, petType);
            case PARROT -> new Parrot(petName, age, room, petType);
            case RABBIT -> new Rabbit(petName, age, room, petType);
        };
    }
}
