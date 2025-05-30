package cz.cvut.fel.omo.sem.house;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.omo.sem.actors.Being;
import cz.cvut.fel.omo.sem.actors.people.PeopleFactory;
import cz.cvut.fel.omo.sem.actors.people.Person;
import cz.cvut.fel.omo.sem.actors.people.PersonType;
import cz.cvut.fel.omo.sem.actors.pets.Pet;
import cz.cvut.fel.omo.sem.actors.pets.PetFactory;
import cz.cvut.fel.omo.sem.actors.pets.PetType;
import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.DeviceFactory;
import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.outside.OutsideGear;
import cz.cvut.fel.omo.sem.devices.outside.OutsideGearType;

import java.io.IOException;
import java.nio.file.Paths;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Class responsible for loading house configuration from JSON file.
 */
public class HouseLoad {

    private static HouseLoad instance;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private HouseLoad() {
    }

    public static synchronized HouseLoad getInstance() {
        if (instance == null) {
            instance = new HouseLoad();
        }
        return instance;
    }

    /**
     * Loads house configuration from JSON file.
     * @param path path to JSON file
     * @return loaded house
     */
    public House loadHouseConfiguration(String path) {
        logger.entering(getClass().getSimpleName(), "loadHouseConfiguration", path);
        try {
            JsonNode jsonNode = objectMapper.readTree(Paths.get(path).toFile());
            return loadHouse(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            logger.exiting(getClass().getSimpleName(), "loadHouseConfiguration");
        }
    }

    private House loadHouse(JsonNode jsonNode) {
        House house = House.getInstance();
        JsonNode floorsNode = jsonNode.get("floors");
        if (floorsNode != null && floorsNode.isArray()) {
            for (JsonNode floorNode : floorsNode) {
                Floor floor = loadFloor(floorNode, house);
                house.addItem(Floor.class, floor);
            }
        }
        return house;
    }

    private Floor loadFloor(JsonNode jsonNode, House house) {
        int level = jsonNode.get("level").asInt();
        Floor floor = new Floor(level, house);
        JsonNode roomsNode = jsonNode.get("rooms");
        if (roomsNode != null && roomsNode.isArray()) {
            for (JsonNode roomNode : roomsNode) {
                Room room = loadRoom(roomNode, floor);
                floor.addItem(Room.class, room);
            }
        }
        return floor;
    }

    private Room loadRoom(JsonNode jsonNode, Floor floor) {
        RoomType type = objectMapper.convertValue(jsonNode.get("type"), RoomType.class);
        Room room = RoomFactory.create(type, floor);
        if (type == RoomType.GARAGE) {
            loadOutsideGear(jsonNode, room);
        }
        loadDevices(jsonNode, room);
        loadWindows(jsonNode, room);
        loadPets(jsonNode, room);
        loadPeople(jsonNode, room);
        return room;
    }

    private void loadOutsideGear(JsonNode jsonNode, Room room) {
        JsonNode outsideGearsNode = jsonNode.get("outsideGear");
        if (outsideGearsNode != null && outsideGearsNode.isArray()) {
            for (JsonNode outsideGearNode : outsideGearsNode) {
                OutsideGearType outsideGearType = objectMapper.convertValue(outsideGearNode.get("type"), OutsideGearType.class);
                room.addItem(OutsideGear.class, new OutsideGear(outsideGearType, room));
            }
        }
    }

    private void loadDevices(JsonNode jsonNode, Room room) {
        JsonNode devicesNode = jsonNode.get("devices");
        if (devicesNode != null && devicesNode.isArray()) {
            for (JsonNode deviceNode : devicesNode) {
                DeviceType deviceType = objectMapper.convertValue(deviceNode.get("type"), DeviceType.class);
                Device device = DeviceFactory.create(deviceType, room);
                room.addItem(Device.class, device);
            }
        }
    }

    private void loadWindows(JsonNode jsonNode, Room room) {
        JsonNode windowsNode = jsonNode.get("windows");
        if (windowsNode != null && windowsNode.isArray()) {
            for (JsonNode ignored : windowsNode) {
                room.addItem(Window.class, new Window());
            }
        }
    }

    private void loadPets(JsonNode jsonNode, Room room) {
        JsonNode petsNode = jsonNode.get("pets");
        if (petsNode != null && petsNode.isArray()) {
            for (JsonNode petNode : petsNode) {
                String name = petNode.get("name").asText();
                int age = petNode.get("age").asInt();
                PetType petType = objectMapper.convertValue(petNode.get("type"), PetType.class);
                Pet pet = PetFactory.create(petType, age, name, room);
                room.addItem(Being.class, pet);
            }
        }
    }

    private void loadPeople(JsonNode jsonNode, Room room) {
        JsonNode peopleNode = jsonNode.get("people");
        if (peopleNode != null && peopleNode.isArray()) {
            for (JsonNode personNode : peopleNode) {
                String name = personNode.get("name").asText();
                int age = personNode.get("age").asInt();
                PersonType personType = objectMapper.convertValue(personNode.get("type"), PersonType.class);
                Person person = PeopleFactory.create(personType, age, name, room);
                room.addItem(Being.class, person);
            }
        }
    }
}
