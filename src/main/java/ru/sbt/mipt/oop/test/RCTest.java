package ru.sbt.mipt.oop.test;


import org.testng.annotations.Test;
import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.Components.Alarm.AlarmEntity;
import ru.sbt.mipt.oop.Components.Alarm.AlarmStatus;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.RemoteControl.Commands.ActivateAlarmCommand;
import ru.sbt.mipt.oop.Components.RemoteControl.Commands.AllLightsOnCommand;
import ru.sbt.mipt.oop.Components.RemoteControl.Commands.CloseHallDoorCommand;
import ru.sbt.mipt.oop.Components.RemoteControl.RemoteControlImpl;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.Components.SmartHome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RCTest {

    SmartHome smartHome;
    RemoteControlImpl remoteControl = new RemoteControlImpl();

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

        smartHome.setAlarmEntity(new AlarmEntity(Application.getUserAlarmCode()));
    }

    @Test
    public void alarmOnTest() {
        before();
        remoteControl.addCommand("A", new ActivateAlarmCommand(smartHome, "code"));
        remoteControl.onButtonPressed("A");
        assertEquals(smartHome.getAlarmEntity().getStatus(), AlarmStatus.ACTIVATED);
    }

    @Test
    public void lightsOnTest() {
        before();
        remoteControl.addCommand("A", new AllLightsOnCommand(smartHome));
        remoteControl.onButtonPressed("A");
        for (Room room : smartHome.getRooms() ) {
            for (Light light : room.getLights() ) {
                assertTrue(light.isOn());
            }
        }
    }

    @Test
    public void closeHallDoorTest() {
        before();
        remoteControl.addCommand("A", new CloseHallDoorCommand(smartHome));
        remoteControl.onButtonPressed("A");
        for (Room room : smartHome.getRooms() ) {
            for (Door door : room.getDoors() ) {
                if (door.getRoomName().equals("hall")) {
                    assertFalse(door.isOpen());
                }
            }
        }
    }



}
