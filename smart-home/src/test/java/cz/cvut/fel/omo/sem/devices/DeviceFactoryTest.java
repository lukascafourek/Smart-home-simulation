package cz.cvut.fel.omo.sem.devices;

import cz.cvut.fel.omo.sem.devices.bathtub.Bathtub;
import cz.cvut.fel.omo.sem.devices.fridge.Fridge;
import cz.cvut.fel.omo.sem.devices.microwave.Microwave;
import cz.cvut.fel.omo.sem.devices.pc.PC;
import cz.cvut.fel.omo.sem.devices.printer.Printer;
import cz.cvut.fel.omo.sem.devices.radiator.Radiator;
import cz.cvut.fel.omo.sem.devices.sensor.Sensor;
import cz.cvut.fel.omo.sem.devices.stove.Stove;
import cz.cvut.fel.omo.sem.devices.tv.TV;
import cz.cvut.fel.omo.sem.devices.wc.WC;
import cz.cvut.fel.omo.sem.house.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceFactoryTest {

    private Room mockRoom;

    @BeforeEach
    public void setUp() {
        mockRoom = Mockito.mock(Room.class);
    }

    @Test
    public void create_createsTVDevice() {
        Device device = DeviceFactory.create(DeviceType.TV, mockRoom);
        assertInstanceOf(TV.class, device);
        assertEquals(DeviceType.TV, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsFridgeDevice() {
        Device device = DeviceFactory.create(DeviceType.FRIDGE, mockRoom);
        assertInstanceOf(Fridge.class, device);
        assertEquals(DeviceType.FRIDGE, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsSensorDevice() {
        Device device = DeviceFactory.create(DeviceType.SENSOR, mockRoom);
        assertInstanceOf(Sensor.class, device);
        assertEquals(DeviceType.SENSOR, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsPCDevice() {
        Device device = DeviceFactory.create(DeviceType.PC, mockRoom);
        assertInstanceOf(PC.class, device);
        assertEquals(DeviceType.PC, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsPrinterDevice() {
        Device device = DeviceFactory.create(DeviceType.PRINTER, mockRoom);
        assertInstanceOf(Printer.class, device);
        assertEquals(DeviceType.PRINTER, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsWCDevice() {
        Device device = DeviceFactory.create(DeviceType.WC, mockRoom);
        assertInstanceOf(WC.class, device);
        assertEquals(DeviceType.WC, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsBathtubDevice() {
        Device device = DeviceFactory.create(DeviceType.BATHTUB, mockRoom);
        assertInstanceOf(Bathtub.class, device);
        assertEquals(DeviceType.BATHTUB, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsStoveDevice() {
        Device device = DeviceFactory.create(DeviceType.STOVE, mockRoom);
        assertInstanceOf(Stove.class, device);
        assertEquals(DeviceType.STOVE, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsMicrowaveDevice() {
        Device device = DeviceFactory.create(DeviceType.MICROWAVE, mockRoom);
        assertInstanceOf(Microwave.class, device);
        assertEquals(DeviceType.MICROWAVE, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }

    @Test
    public void create_createsRadiatorDevice() {
        Device device = DeviceFactory.create(DeviceType.RADIATOR, mockRoom);
        assertInstanceOf(Radiator.class, device);
        assertEquals(DeviceType.RADIATOR, device.getDeviceType());
        assertEquals(mockRoom, device.getRoom());
    }
}
