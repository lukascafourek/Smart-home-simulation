package cz.cvut.fel.omo.sem.reports;

import cz.cvut.fel.omo.sem.house.House;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Singleton class that manages the generation of all reports.
 */
public class ReportManager {

    private static ReportManager instance;

    private ReportManager() {
    }

    public static synchronized ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    /**
     * Generates all reports.
     * Facade pattern is used to hide the complexity of the report generation process.
     * @param house     house to generate reports for
     * @param outputPath path to the directory where the reports will be saved
     */
    public void generateReports(House house, String outputPath) {
        logger.entering(getClass().getSimpleName(), "generateReports");
        HouseConfigurationReport.getInstance().generateReport(house, outputPath);
        EventReport.getInstance().generateReport(outputPath);
        ActivityAndUsageReport.getInstance().generateReport(outputPath);
        ConsumptionReport.getInstance().generateReport(outputPath);
        logger.exiting(getClass().getSimpleName(), "generateReports");
    }
}
