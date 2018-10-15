package ru.sbt.mipt.oop;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;

//        smartHome.execute(object -> {
//            if (object instanceof Room) {
//                Room room = (Room) object;
//                Light light = room.getLightById(event.getObjectId());
//                if (event.getType() == LIGHT_ON) {
//                    light.setOn(true);
//                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
//                } else {
//                    light.setOn(false);
//                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
//                }
//            }
//        });

        for (Room room : smartHome.getRooms()) {
            Light light = room.getLightById(event.getObjectId());
            if (event.getType() == LIGHT_ON) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
