package cz.cvut.fel.omo.sem.reports;

import cz.cvut.fel.omo.sem.events.EventObservableImpl;

import java.io.FileWriter;
import java.io.IOException;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Report that contains information about the events that occurred in the simulation.
 * Singleton class.
 */
class EventReport {

    private static EventReport instance;

    private EventReport() {
    }

    static synchronized EventReport getInstance() {
        if (instance == null) {
            instance = new EventReport();
        }
        return instance;
    }

    void generateReport(String outputPath) {
        logger.entering(getClass().getSimpleName(), "generateReport");
        try {
            FileWriter myWriter = new FileWriter(outputPath + "EventReport.txt");
            myWriter.write(EventObservableImpl.getInstance().getStringBuilder().toString());
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            logger.exiting(getClass().getSimpleName(), "generateReport");
        }
    }
}
