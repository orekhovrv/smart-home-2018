package ru.sbt.mipt.oop.test;

import org.junit.Before;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.Components.SmartHome;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class ComposIteIteratorTest {

    SmartHome smartHome;
    Set<Object> objects;

    @Before
    public void before() {
        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(new Door(true, "1", "hall"));
        doors1.add(new Door(false, "2", "hall"));

        List<Light> lights1 = new ArrayList<>();
        lights1.add(new Light("1", true, "hall"));
        lights1.add(new Light("2", false, "hall"));

        Collection<Door> doors2 = new ArrayList<>();
        doors2.add(new Door(true, "3", "kitchen"));
        doors2.add(new Door(false, "4", "kitchen"));

        List<Light> lights2 = new ArrayList<>();
        lights2.add(new Light("3", true, "kitchen"));
        lights2.add(new Light("4", false, "kitchen"));

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(lights1, doors1, "hall"));
        rooms.add(new Room(lights2, doors2, "kitchen"));

        smartHome = new SmartHome(rooms);

        objects = new HashSet<>();
        objects.add(smartHome);
        objects.addAll(doors1);
        objects.addAll(doors2);
        objects.addAll(lights1);
        objects.addAll(lights2);
    }

    @Test
    public void Test() {
        before();

        smartHome.execute(object -> objects.remove(object));

        assertTrue(objects.isEmpty());
    }
}
