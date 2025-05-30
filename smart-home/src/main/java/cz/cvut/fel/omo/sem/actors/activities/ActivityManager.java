package cz.cvut.fel.omo.sem.actors.activities;

import cz.cvut.fel.omo.sem.actors.Being;
import cz.cvut.fel.omo.sem.actors.people.Person;
import cz.cvut.fel.omo.sem.actors.people.PersonState;
import cz.cvut.fel.omo.sem.actors.pets.Pet;
import cz.cvut.fel.omo.sem.devices.Device;
import cz.cvut.fel.omo.sem.devices.outside.OutsideGear;
import cz.cvut.fel.omo.sem.devices.outside.OutsideGearType;

import java.util.List;
import java.util.Random;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Class that manages activities of a person.
 */
public class ActivityManager {

    private Device currentDevice = null;
    private OutsideGear currentOutsideGear = null;
    private final Person person;

    public ActivityManager(Person person) {
        this.person = person;
    }

    private <T> void handleActivity(ActivityType activity, T instance) {
        if (instance instanceof Pet pet) {
            handlePetActivity(activity, pet);
        } else if (instance instanceof Device device) {
            handleDeviceActivity(activity, device);
        } else if (instance instanceof OutsideGear outsideGear) {
            handleOutsideGearActivity(activity, outsideGear);
        }
    }

    private void handlePetActivity(ActivityType activity, Pet pet) {
        logger.entering(ActivityManager.class.getSimpleName(), "handlePetActivity", new Object[]{activity, pet, person});
        person.setRoom(pet.getRoom());
        logger.info(person.getPersonType().toString() + " " + person.getName() + " is handling activity " +
                activity.toString() + " for " + pet.getName() + " the " + pet.getPetType().toString());
        switch (activity) {
            case PLAYING -> pet.play();
            case FEEDING -> pet.feed();
            default -> throw new IllegalStateException("Unknown activity: " + activity);
        }
        increaseUsage(pet);
        logger.exiting(ActivityManager.class.getName(), "handlePetActivity");
    }

    private void handleDeviceActivity(ActivityType type, Device device) {
        logger.entering(ActivityManager.class.getSimpleName(), "handleDeviceActivity", new Object[]{type, device, person});
        stopActivity();
        person.setRoom(device.getRoom());
        logger.info(person.getPersonType().toString() + " " + person.getName() + " is handling activity " +
                type.toString() + " for " + device.getDeviceType().toString());
        device.setUnavailable();
        switch (type) {
            case USING -> device.use();
            case CLEANING -> device.clean();
            default -> throw new IllegalStateException("Unknown activity: " + type);
        }
        currentDevice = device;
        increaseUsage(device);
        logger.exiting(ActivityManager.class.getSimpleName(), "handleDeviceActivity");
    }

    private void handleOutsideGearActivity(ActivityType type, OutsideGear outsideGear) {
        logger.entering(ActivityManager.class.getSimpleName(), "handleOutsideGearActivity", new Object[]{type, outsideGear, person});
        stopActivity();
        person.setRoom(outsideGear.getRoom());
        logger.info(person.getPersonType().toString() + " " + person.getName() + " is handling activity " +
                type.toString() + " for " + outsideGear.getType().toString());
        if (type == ActivityType.GOING_OUTSIDE) {
            person.getRoom().removeItem(Being.class, person);
            person.setState(PersonState.OUTSIDE);
            outsideGear.setUnavailable();
            outsideGear.goOutside(person);
        } else {
            throw new IllegalStateException("Unknown activity: " + type);
        }
        currentOutsideGear = outsideGear;
        increaseUsage(outsideGear);
        logger.exiting(ActivityManager.class.getSimpleName(), "handleOutsideGearActivity");
    }

    private <T> void increaseUsage(T instance) {
        if (instance instanceof Device) {
            person.getDeviceUsage().put((Device) instance, person.getDeviceUsage().getOrDefault(instance, 0) + 1);
        } else if (instance instanceof OutsideGear) {
            person.getOutsideGearUsage().put((OutsideGear) instance, person.getOutsideGearUsage().getOrDefault(instance, 0) + 1);
        } else if (instance instanceof Pet) {
            person.getPetUsage().put((Pet) instance, person.getPetUsage().getOrDefault(instance, 0) + 1);
        }
    }

    /**
     * Stops current activity of a person.
     */
    public void stopActivity() {
        if (currentDevice != null) {
            currentDevice.stopUsing();
            currentDevice = null;
        }
        if (currentOutsideGear != null) {
            currentOutsideGear.stopUsing();
            currentOutsideGear = null;
        }
    }

    /**
     * Selects a random device and handles activity for it.
     * @param activityType activity to handle
     */
    public void selectRandomDevice(ActivityType activityType) {
        logger.entering(ActivityManager.class.getSimpleName(), "selectRandomDevice", activityType);
        List<Device> devices = person.getRoom().getItems(Device.class).stream()
                .filter(Device::isAvailable).toList();
        if (!devices.isEmpty()) {
            handleActivity(activityType, devices.get(new Random().nextInt(devices.size())));
        } else {
            person.update();
        }
        logger.exiting(ActivityManager.class.getSimpleName(), "selectRandomDevice");
    }

    /**
     * Selects a random pet and handles activity for it.
     * @param activityType activity to handle
     */
    public void selectRandomPet(ActivityType activityType) {
        logger.entering(ActivityManager.class.getSimpleName(), "selectRandomPet", activityType);
        List<Being> pets = person.getRoom().getItems(Being.class);
        if (!pets.isEmpty()) {
            handleActivity(activityType, pets.get(new Random().nextInt(pets.size())));
        } else {
            person.update();
        }
        logger.exiting(ActivityManager.class.getSimpleName(), "selectRandomPet");
    }

    /**
     * Selects a random outside gear and handles activity for it.
     */
    public void selectRandomOutsideGear() {
        logger.entering(ActivityManager.class.getSimpleName(), "selectRandomOutsideGear");
        List<OutsideGear> outsideGears = person.getRoom().getItems(OutsideGear.class).stream()
                .filter(OutsideGear::isAvailable)
                .filter(outsideGear -> {
                    if (person.getAge() < 18) {
                        return outsideGear.getType() != OutsideGearType.CAR;
                    }
                    return true;
                }).toList();
        if (!outsideGears.isEmpty()) {
            handleActivity(ActivityType.GOING_OUTSIDE, outsideGears.get(new Random().nextInt(outsideGears.size())));
        } else {
            person.update();
        }
        logger.exiting(ActivityManager.class.getSimpleName(), "selectRandomOutsideGear");
    }
}
