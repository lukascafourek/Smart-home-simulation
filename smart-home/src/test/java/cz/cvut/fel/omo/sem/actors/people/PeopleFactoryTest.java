package cz.cvut.fel.omo.sem.actors.people;

import cz.cvut.fel.omo.sem.house.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PeopleFactoryTest {

    private Room mockRoom;

    @BeforeEach
    public void setUp() {
        mockRoom = Mockito.mock(Room.class);
    }

    @Test
    public void create_createsFather() {
        Person person = PeopleFactory.create(PersonType.FATHER, 40, "John", mockRoom);
        assertInstanceOf(Father.class, person);
        assertEquals(PersonType.FATHER, person.getPersonType());
        assertEquals(40, person.getAge());
        assertEquals("John", person.getName());
        assertEquals(mockRoom, person.getRoom());
    }

    @Test
    public void create_createsMother() {
        Person person = PeopleFactory.create(PersonType.MOTHER, 38, "Jane", mockRoom);
        assertInstanceOf(Mother.class, person);
        assertEquals(PersonType.MOTHER, person.getPersonType());
        assertEquals(38, person.getAge());
        assertEquals("Jane", person.getName());
        assertEquals(mockRoom, person.getRoom());
    }

    @Test
    public void create_createsBaby() {
        Person person = PeopleFactory.create(PersonType.BABY, 1, "Baby John", mockRoom);
        assertInstanceOf(Baby.class, person);
        assertEquals(PersonType.BABY, person.getPersonType());
        assertEquals(1, person.getAge());
        assertEquals("Baby John", person.getName());
        assertEquals(mockRoom, person.getRoom());
    }

    @Test
    public void create_createsChild() {
        Person person = PeopleFactory.create(PersonType.CHILD, 10, "Child John", mockRoom);
        assertInstanceOf(Child.class, person);
        assertEquals(PersonType.CHILD, person.getPersonType());
        assertEquals(10, person.getAge());
        assertEquals("Child John", person.getName());
        assertEquals(mockRoom, person.getRoom());
    }

    @Test
    public void create_createsTeenager() {
        Person person = PeopleFactory.create(PersonType.TEENAGER, 16, "Teen John", mockRoom);
        assertInstanceOf(Teenager.class, person);
        assertEquals(PersonType.TEENAGER, person.getPersonType());
        assertEquals(16, person.getAge());
        assertEquals("Teen John", person.getName());
        assertEquals(mockRoom, person.getRoom());
    }
}
