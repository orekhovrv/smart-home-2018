package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) return;
        // событие от двери

//        for (Room room : smartHome.getRooms()) {
//            for (Door door : room.getDoors()) {
//                if (door.getId().equals(event.getObjectId())) {
//                    if (event.getType() == DOOR_OPEN) {
//                        changeDoorState(room, door, true, " was opened.");
//                    } else {
//                        changeDoorState(room, door, false, " was closed.");
//                    }
//                }
//            }
//        }

        smartHome.execute(object -> {       // my code
            if (object instanceof Room) {
                Room room = (Room) object;
            }
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

//    private void changeDoorState(Room room, Door door, boolean opened, String s) {
//        door.setOpen(opened);
//        System.out.println("Door " + door.getId() + " in room " + room.getName() + s);
//    }

    private void changeDoorState(Door door, boolean opened, String s) {     // my code
        door.setOpen(opened);
        System.out.println("Door " + door.getId() + s);
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
