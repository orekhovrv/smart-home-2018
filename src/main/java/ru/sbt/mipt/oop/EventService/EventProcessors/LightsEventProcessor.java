package ru.sbt.mipt.oop.EventService.EventProcessors;

import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.EventService.SensorEvent;
import ru.sbt.mipt.oop.Components.SmartHome;

import static ru.sbt.mipt.oop.EventService.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.EventService.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;

        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                Light light = room.getLightById(event.getObjectId());
                if (light != null) {        //my code
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        });
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
