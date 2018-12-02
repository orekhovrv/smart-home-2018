package ru.sbt.mipt.oop.Adapters;


import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Adapter implements EventsManager {

    private Map<String, SensorEventType> sensorEventTypeMap = new HashMap<>();
    private Collection<EventProcessor> eventProcessors = new ArrayList<>();
    SensorEventsManager sensorEventsManager;

    public Adapter(SensorEventsManager sensorEventsManager) {
        sensorEventTypeMap.put("LightIsOn", SensorEventType.LIGHT_ON);
        sensorEventTypeMap.put("LightIsOff", SensorEventType.LIGHT_OFF);
        sensorEventTypeMap.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        sensorEventTypeMap.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        sensorEventTypeMap.put("DoorIsLocked", SensorEventType.ALARM_ACTIVATE);
        sensorEventTypeMap.put("DoorIsUnlocked", SensorEventType.ALARM_DEACTIVATE);

        this.sensorEventsManager = sensorEventsManager;
    }

    SensorEvent fromApiEventtoHomeEvent(CCSensorEvent apiEvent) {
        SensorEventType eventType = sensorEventTypeMap.get(apiEvent.getEventType());
        return new SensorEvent(eventType, apiEvent.getObjectId());
    }

    @Override
    public void registerEventProcessor(EventProcessor eventProcessor) { eventProcessors.add(eventProcessor); }

    @Override
    public void runEventsCycle(SmartHome smartHome) {
        sensorEventsManager.registerEventHandler(event -> {
            System.out.println("Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId() + "]");
            SensorEvent sensorEvent = fromApiEventtoHomeEvent(event);
            for (EventProcessor eventProcessor: eventProcessors) {
                eventProcessor.processEvent(smartHome, sensorEvent);
            }
        });
        sensorEventsManager.start();
    }
}
