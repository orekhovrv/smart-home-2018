package ru.sbt.mipt.oop.Components;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Executable;

public class Door implements Executable {
    private final String id;
    private boolean isOpen;
    private String roomName;      // my code



    public Door(boolean isOpen, String id, String roomName) {
        this.isOpen = isOpen;
        this.id = id;
        this.roomName = roomName;       //my code
    }

    public String getRoomName() {       // my code
        return roomName;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.executeAction(this);
    }
}
