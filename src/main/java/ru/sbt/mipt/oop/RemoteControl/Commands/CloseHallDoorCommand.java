package ru.sbt.mipt.oop.RemoteControl.Commands;


import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.Components.SmartHome;

public class CloseHallDoorCommand implements Command {

    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if ("hall".equals(door.getRoomName())) {
                    door.setOpen(false);
                }
            }
        });
    }

}

