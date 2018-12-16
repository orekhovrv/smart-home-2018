package ru.sbt.mipt.oop.test;


import org.junit.Before;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.Components.SmartHome;
import ru.sbt.mipt.oop.EventService.EventProcessors.DoorEventProcessor;
import ru.sbt.mipt.oop.EventService.EventProcessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.EventService.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static ru.sbt.mipt.oop.EventService.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessorTest {

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
    public void closeHallDoorTest(){
        before();
        event = new SensorEvent(DOOR_CLOSED, "1");
        HallDoorEventProcessor processor = new HallDoorEventProcessor();

        processor.processEvent(smartHome, event);

        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            for(Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }


}
