package cz.cvut.fel.omo.sem.devices;

import cz.cvut.fel.omo.sem.events.*;
import cz.cvut.fel.omo.sem.house.Room;
import cz.cvut.fel.omo.sem.interfaces.Updatable;
import cz.cvut.fel.omo.sem.reports.ConsumptionReport;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Abstract class representing a device in the house.
 */
public abstract class Device implements Updatable {

    protected Repair repairManual;
    private final DeviceType type;
    private boolean availability = true;
    /**
     * Electricity usage per use of the device.
     */
    protected int electricityPerUse = 0;
    /**
     * Water usage per use of the device.
     */
    protected int waterPerUse = 0;
    /**
     * Gas usage per use of the device.
     */
    protected int gasPerUse = 0;
    private final int maxDurability = 100;
    private final int afterServiceDurability = 80;
    private final int afterSelfRepairDurability = 60;
    private int durability = maxDurability;
    private final Room room;
    private final ResourceUsage resourceUsage = new ResourceUsage();

    public Device(DeviceType type, Room room) {
        this.type = type;
        this.room = room;
        ConsumptionReport.getInstance().addDevice(this);
    }

    public ResourceUsage getResourceUsage() {
        return resourceUsage;
    }

    /**
     * Method to use resources by the device.
     */
    public void useResources() {
        resourceUsage.addElectricity(electricityPerUse);
        resourceUsage.addWater(waterPerUse);
        resourceUsage.addGas(gasPerUse);
    }

    public boolean isAvailable() {
        return availability;
    }

    /**
     * Method to clean the device.
     */
    public void clean() {
        durability += 10;
        if (durability > maxDurability) {
            durability = maxDurability;
        }
    }

    /**
     * Method to use the device.
     */
    public abstract void use();

    public void setUnavailable() {
        availability = false;
    }

    public void stopUsing() {
        availability = true;
    }

    /**
     * Method to build a report about the device.
     * @param sb  StringBuilder to append the report to
     * @param tab number of tabs to indent the report
     */
    public void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        sb.append("\t".repeat(tab)).append(type.toString()).append("\n");
    }

    public Room getRoom() {
        return room;
    }

    public DeviceType getDeviceType() {
        return type;
    }

    /**
     * Method to repair the device.
     */
    public void repair() {
        logger.info(getDeviceType().toString() + " repaired by " + repairManual.toString() + " method in manual");
        switch (repairManual) {
            case SELFREPAIR -> durability = afterSelfRepairDurability;
            case CALLSERVICE -> durability = afterServiceDurability;
            case NEWPRODUCT -> durability = maxDurability;
        }
    }

    /**
     * Method to do damage to the device.
     * @param damage amount of damage to do
     */
    public void doDamage(int damage) {
        durability -= damage;
        if (durability <= 0 && EventManager.getInstance().eventNotInQueue(EventType.BREAK, this)) {
            EventObservableImpl.getInstance().notifyEvent(new Event<>(EventType.BREAK, this, EventObservableImpl.eventIndex++));
        }
    }

    @Override
    public void update() {
        useResources();
        doDamage(1);
    }

    public int getDurability() {
        return durability;
    }

    public void setRepairManual(Repair repairManual) {
        this.repairManual = repairManual;
    }
}
