package ru.sbt.mipt.oop.Components;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Executable;

public class Light implements Executable {
    private boolean isOn;
    private final String id;
    private String roomName;

    public Light(String id, boolean isOn, String roomName) {
        this.id = id;
        this.isOn = isOn;
        this.roomName = roomName;
    }

    public String getRoomName() {       // my code
        return roomName;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        action.executeAction(this);
    }

}
