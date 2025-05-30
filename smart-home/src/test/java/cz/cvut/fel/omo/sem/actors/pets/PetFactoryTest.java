package cz.cvut.fel.omo.sem.actors.pets;

import cz.cvut.fel.omo.sem.house.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PetFactoryTest {

    private Room mockRoom;

    @BeforeEach
    public void setUp() {
        mockRoom = Mockito.mock(Room.class);
    }

    @Test
    public void create_createsCat() {
        Pet pet = PetFactory.create(PetType.CAT, 2, "Whiskers", mockRoom);
        assertInstanceOf(Cat.class, pet);
        assertEquals(PetType.CAT, pet.getPetType());
        assertEquals(2, pet.getAge());
        assertEquals("Whiskers", pet.getName());
        assertEquals(mockRoom, pet.getRoom());
    }

    @Test
    public void create_createsDog() {
        Pet pet = PetFactory.create(PetType.DOG, 3, "Buddy", mockRoom);
        assertInstanceOf(Dog.class, pet);
        assertEquals(PetType.DOG, pet.getPetType());
        assertEquals(3, pet.getAge());
        assertEquals("Buddy", pet.getName());
        assertEquals(mockRoom, pet.getRoom());
    }

    @Test
    public void create_createsHamster() {
        Pet pet = PetFactory.create(PetType.HAMSTER, 1, "Nibbles", mockRoom);
        assertInstanceOf(Hamster.class, pet);
        assertEquals(PetType.HAMSTER, pet.getPetType());
        assertEquals(1, pet.getAge());
        assertEquals("Nibbles", pet.getName());
        assertEquals(mockRoom, pet.getRoom());
    }

    @Test
    public void create_createsParrot() {
        Pet pet = PetFactory.create(PetType.PARROT, 4, "Polly", mockRoom);
        assertInstanceOf(Parrot.class, pet);
        assertEquals(PetType.PARROT, pet.getPetType());
        assertEquals(4, pet.getAge());
        assertEquals("Polly", pet.getName());
        assertEquals(mockRoom, pet.getRoom());
    }

    @Test
    public void create_createsRabbit() {
        Pet pet = PetFactory.create(PetType.RABBIT, 2, "Thumper", mockRoom);
        assertInstanceOf(Rabbit.class, pet);
        assertEquals(PetType.RABBIT, pet.getPetType());
        assertEquals(2, pet.getAge());
        assertEquals("Thumper", pet.getName());
        assertEquals(mockRoom, pet.getRoom());
    }
}
