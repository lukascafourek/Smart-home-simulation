package cz.cvut.fel.omo.sem.devices.fridge;

import cz.cvut.fel.omo.sem.devices.DeviceType;
import cz.cvut.fel.omo.sem.devices.Repair;
import cz.cvut.fel.omo.sem.devices.ResourceUsage;
import cz.cvut.fel.omo.sem.house.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FridgeTest {

    private Fridge fridge;

    @BeforeEach
    public void setUp() {
        Room mockRoom = Mockito.mock(Room.class);
        fridge = new Fridge(DeviceType.FRIDGE, mockRoom);
    }

    @Test
    public void refillFridgeTest() {
        assertEquals(5, fridge.getRations());
        fridge.setRations(0);
        fridge.refillFridge();
        assertEquals(5, fridge.getRations());
    }

    @Test
    public void useResourcesTest() {
        fridge.useResources();
        ResourceUsage usage = fridge.getResourceUsage();
        assertEquals(100, usage.getElectricity());
        assertEquals(0, usage.getWater());
        assertEquals(0, usage.getGas());
    }

    @Test
    public void doDamageTest() {
        assertEquals(100, fridge.getDurability());
        fridge.doDamage(10);
        assertEquals(90, fridge.getDurability());
    }

    @Test
    public void cleanTest() {
        fridge.doDamage(20);
        fridge.clean();
        assertEquals(90, fridge.getDurability());
    }

    @Test
    public void cleanMaxDurabilityTest() {
        assertEquals(100, fridge.getDurability());
        fridge.clean();
        assertEquals(100, fridge.getDurability());
    }

    @Test
    public void repairNewProductTest() {
        fridge.setRepairManual(Repair.NEWPRODUCT);
        fridge.repair();
        assertEquals(100, fridge.getDurability());
    }

    @Test
    public void repairCallServiceTest() {
        fridge.setRepairManual(Repair.CALLSERVICE);
        fridge.repair();
        assertEquals(80, fridge.getDurability());
    }

    @Test
    public void repairSelfRepair() {
        fridge.setRepairManual(Repair.SELFREPAIR);
        fridge.repair();
        assertEquals(60, fridge.getDurability());
    }
}
