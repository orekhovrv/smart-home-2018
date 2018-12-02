package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventsObserver {
    private Collection<EventProcessor> configureEventProcessors() {
        Collection<EventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new LightsEventProcessor());
        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallDoorEventProcessor());
        eventProcessors.add(new AlarmEventProcessor(Application.getUserAlarmCode()));
        return eventProcessors;
    }

    public void runEventsCycle(SmartHome smartHome) {
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();
        Collection<EventProcessor> eventProcessors = this.configureEventProcessors();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (!smartHome.getAlarm().getBehavior().isIgnoringEvents()) {
                for (EventProcessor eventProcessor : eventProcessors) {
                    eventProcessor.processEvent(smartHome, event);
                }
            } else {
                smartHome.getAlarm().getBehavior().sendSMS();
            }
            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }
}
