package cz.cvut.fel.omo.sem.actors.people;

import cz.cvut.fel.omo.sem.house.Room;

/**
 * Factory class for creating types of people.
 */
public class PeopleFactory {

    /**
     * Creates a person of the given type.
     * @param personType type of person to create
     * @param age age of the person
     * @param personName name of the person
     * @param room room the person is in
     * @return created person
     */
    public static Person create(PersonType personType, int age, String personName, Room room) {
        return switch (personType) {
            case FATHER -> new Father(personName, age, room, personType);
            case MOTHER -> new Mother(personName, age, room, personType);
            case BABY -> new Baby(personName, age, room, personType);
            case CHILD -> new Child(personName, age, room, personType);
            case TEENAGER -> new Teenager(personName, age, room, personType);
        };
    }
}
