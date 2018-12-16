package ru.sbt.mipt.oop.EventService.EventProcessors;

import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.EventService.SensorEvent;
import ru.sbt.mipt.oop.Components.SmartHome;

import static ru.sbt.mipt.oop.EventService.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.EventService.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) return;

        smartHome.execute(object -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        changeDoorState(door, true, " was opened.");
                    } else {
                        changeDoorState(door, false, " was closed.");
                    }
                }
            }
        });
    }

    private void changeDoorState(Door door, boolean opened, String s) {     // my code
        door.setOpen(opened);
        System.out.println("Door " + door.getId() + s);
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
