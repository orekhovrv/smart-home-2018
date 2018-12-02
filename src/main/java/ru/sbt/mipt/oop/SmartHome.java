package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Executable {

    private Alarm alarm;
    private  Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.rooms = rooms;
        this.alarm = alarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Alarm getAlarm() { return alarm; }

    public void setAlarm(Alarm alarm) { this.alarm = alarm; }

    public void turnOffLights() {
        this.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                SensorCommandExecutor.executeCommand(command);
            }
        });
    }

    @Override
    public void execute(Action action) {
        action.executeAction(this);
        for (Room room : this.getRooms())
            room.execute(action);
    }

}
