package ru.sbt.mipt.oop.EventService.EventProcessors;

import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.EventService.SensorEvent;
import ru.sbt.mipt.oop.Components.SmartHome;

import static ru.sbt.mipt.oop.EventService.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) return;

        smartHome.execute(object -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(event.getObjectId())) {
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    if (door.getRoomName().equals("hall")) {
                        smartHome.turnLights(false);
                    }
                }
            }
        });
    }
}
