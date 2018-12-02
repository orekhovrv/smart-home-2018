package ru.sbt.mipt.oop.Components;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Components.Alarm.AlarmEntity;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Executable {

    private AlarmEntity alarmEntity;
    private  Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome(Collection<Room> rooms, AlarmEntity alarmEntity) {
        this.rooms = rooms;
        this.alarmEntity = alarmEntity;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public AlarmEntity getAlarmEntity() { return alarmEntity; }

    public void setAlarmEntity(AlarmEntity alarmEntity) { this.alarmEntity = alarmEntity; }

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
