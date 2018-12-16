package ru.sbt.mipt.oop.test;


import org.junit.Before;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.Components.SmartHome;
import ru.sbt.mipt.oop.EventService.EventProcessors.DoorEventProcessor;
import ru.sbt.mipt.oop.EventService.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.sbt.mipt.oop.EventService.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.EventService.SensorEventType.DOOR_OPEN;

public class DoorEventProcessorTest {

    private SensorEvent event;
    private SmartHome smartHome;

    @Before
    public void before() {
        Collection<Door> doors = new ArrayList<>();
        doors.add(new Door(true, "1", "hall"));
        doors.add(new Door(false, "2", "hall"));
        List<Light> lights = new ArrayList<>();
        lights.add(new Light("1", true, "hall"));
        lights.add(new Light("2", false, "hall"));
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(lights, doors, "hall"));

        smartHome = new SmartHome(rooms);
    }

    @Test
    public void closeDoorTest(){
        before();
        event = new SensorEvent(DOOR_CLOSED, "1");
        DoorEventProcessor processor = new DoorEventProcessor();

        processor.processEvent(smartHome, event);

        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            Door door = room.getDoorById("1");
            assertFalse(door.isOpen());
        }
    }

    @Test
    public void openDoorTest() {
        before();
        event = new SensorEvent(DOOR_OPEN, "2");
        DoorEventProcessor processor = new DoorEventProcessor();

        processor.processEvent(smartHome, event);

        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            Door door = room.getDoorById("2");
            assertTrue(door.isOpen());
        }
    }
}
