package cz.cvut.fel.omo.sem.reports;

import cz.cvut.fel.omo.sem.house.House;

import java.io.FileWriter;
import java.io.IOException;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Report that contains information about the configuration of the house.
 * Singleton class.
 */
class HouseConfigurationReport {

    private static HouseConfigurationReport instance;

    private HouseConfigurationReport() {
    }

    static synchronized HouseConfigurationReport getInstance() {
        if (instance == null) {
            instance = new HouseConfigurationReport();
        }
        return instance;
    }

    void generateReport(House house, String outputPath) {
        logger.entering(getClass().getSimpleName(), "generateReport");
        try {
            FileWriter myWriter = new FileWriter(outputPath + "HouseConfigurationReport.txt");
            StringBuilder sb = new StringBuilder();
            house.buildHouseConfigurationReport(sb, 0);
            myWriter.write(sb.toString());
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            logger.exiting(getClass().getSimpleName(), "generateReport");
        }
    }
}
