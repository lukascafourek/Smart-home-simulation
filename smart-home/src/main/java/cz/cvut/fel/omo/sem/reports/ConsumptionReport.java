package cz.cvut.fel.omo.sem.reports;

import cz.cvut.fel.omo.sem.devices.Device;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Report that contains information about the consumption of resources by devices.
 * Singleton class.
 */
public class ConsumptionReport {

    private static ConsumptionReport instance;
    private final List<Device> devices = new ArrayList<>();
    // based on real costs in the Czech Republic without VAT
    private final double electricityCost = 3.37;
    private final double waterCost = 127.10;
    private final double gasCost = 14.98;

    private ConsumptionReport() {
    }

    public static synchronized ConsumptionReport getInstance() {
        if (instance == null) {
            instance = new ConsumptionReport();
        }
        return instance;
    }

    void generateReport(String outputPath) {
        logger.entering(getClass().getSimpleName(), "generateReport");
        try {
            double electricityCostSum = 0;
            double waterCostSum = 0;
            double gasCostSum = 0;
            FileWriter myWriter = new FileWriter(outputPath + "ConsumptionReport.txt");
            StringBuilder sb = new StringBuilder();
            for (Device device : devices) {
                double electricity =  (double) device.getResourceUsage().getElectricity() / 1000;    // wh to kwh
                double water = (double) device.getResourceUsage().getWater() / 1000;    // l to m^3
                double gas = (double) device.getResourceUsage().getGas() / 1000;    // dm^3 to m^3
                electricityCostSum += electricity * electricityCost;
                waterCostSum += water * waterCost;
                gasCostSum += gas * gasCost;
                sb.append(device.getDeviceType().toString()).append(" used:\n")
                        .append("\t").append(String.format("%.1f", electricity)).append(" kWh of electricity.\n")
                        .append("\t").append(String.format("%.1f", water)).append(" m^3 of water.\n")
                        .append("\t").append(String.format("%.1f", gas)).append(" m^3 of gas.\n");
                sb.append("\tCost:\n")
                        .append("\t\t").append(String.format("%.1f", electricity * electricityCost)).append(" CZK for electricity.\n")
                        .append("\t\t").append(String.format("%.1f", water * waterCost)).append(" CZK for water.\n")
                        .append("\t\t").append(String.format("%.1f", gas * gasCost)).append(" CZK for gas.\n");
                sb.append("\n");
            }
            sb.append("Total cost:\n")
                    .append("\t").append(String.format("%.1f",electricityCostSum)).append(" CZK for electricity.\n")
                    .append("\t").append(String.format("%.1f", waterCostSum)).append(" CZK for water.\n")
                    .append("\t").append(String.format("%.1f", gasCostSum)).append(" CZK for gas.\n")
                    .append("\t").append(String.format("%.1f", electricityCostSum + waterCostSum + gasCostSum))
                    .append(" CZK in total.\n");
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
     * Adds a device to list of devices to generate the report for.
     * @param device device to add
     */
    public void addDevice(Device device) {
        devices.add(device);
    }
}
