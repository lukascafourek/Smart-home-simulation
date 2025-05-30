package cz.cvut.fel.omo.sem.reports;

import cz.cvut.fel.omo.sem.actors.people.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Report that contains information about the usage of devices, pets and outside gear by people.
 * Singleton class.
 */
public class ActivityAndUsageReport {

    private static ActivityAndUsageReport instance;
    private final List<Person> people = new ArrayList<>();

    private ActivityAndUsageReport() {
    }

    public static synchronized ActivityAndUsageReport getInstance() {
        if (instance == null) {
            instance = new ActivityAndUsageReport();
        }
        return instance;
    }

    void generateReport(String outputPath) {
        logger.entering(getClass().getSimpleName(), "generateReport");
        try {
            FileWriter myWriter = new FileWriter(outputPath + "ActivityAndUsageReport.txt");
            StringBuilder sb = new StringBuilder();
            for (Person person : people) {
                sb.append(person.getPersonType().toString()).append(" ").append(person.getName())
                        .append(": ").append(person.getAge()).append(" years old used:\n");
                person.getPetUsage().forEach((pet, usage) ->
                        sb.append("\t").append(pet.getPetType().toString())
                                .append(" ").append(pet.getName()).append(" ").append(usage).append(" times\n"));
                person.getDeviceUsage().forEach((device, usage) ->
                        sb.append("\t").append(device.getDeviceType().toString())
                                .append(" ").append(usage).append(" times\n"));
                person.getOutsideGearUsage().forEach((gear, usage) ->
                        sb.append("\t").append(gear.getType().toString())
                                .append(" ").append(usage).append(" times\n"));
                sb.append("\n");
            }
            myWriter.write(sb.toString());
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            logger.exiting(getClass().getSimpleName(), "generateReport");
        }
    }

    /**
     * Adds a person to list of people to generate the report for.
     * @param person person to add
     */
    public void addPerson(Person person) {
        people.add(person);
    }
}
