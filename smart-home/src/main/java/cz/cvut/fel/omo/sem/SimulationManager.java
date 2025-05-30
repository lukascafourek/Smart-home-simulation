package cz.cvut.fel.omo.sem;

import cz.cvut.fel.omo.sem.house.House;
import cz.cvut.fel.omo.sem.house.HouseLoad;
import cz.cvut.fel.omo.sem.reports.ReportManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simulation manager class that runs the simulation for amount of ticks.
 * Singleton pattern.
 */
public class SimulationManager {

    private static SimulationManager instance;
    private final int maxTick = 1000;
    private final int delay = 10;
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private SimulationManager() {
    }

    public static synchronized SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }
        return instance;
    }

    void run() {
        logger.setLevel(Level.FINEST);
        String s = File.separator;
        String outputPath = System.getProperty("user.dir") + s + "smart-home" + s + "generated-reports";
        File outputDirectory = new File(outputPath);
        if (!outputDirectory.exists()) {
            if (!outputDirectory.mkdirs()) {
                throw new RuntimeException("Failed to create output directory");
            }
        }
        try {
            logger.addHandler(new FileHandler(outputDirectory + s + "logs.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Starting simulation");
        logger.info("Available files: test, smallerHouse, biggerHouse");
        logger.info("Enter the name of the file without .json with the house configuration:");
        Scanner scanner = new Scanner(System.in);
        String path = System.getProperty("user.dir") + s + "smart-home" + s + "src" + s + "main" + s + "resources" + s + scanner.nextLine() + ".json";
        House house = HouseLoad.getInstance().loadHouseConfiguration(path);
        for (int tick = 1; tick <= maxTick; tick++) {
            try {
                house.update();
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ReportManager.getInstance().generateReports(house, outputPath + s);
        logger.info("Simulation finished");
    }
}
