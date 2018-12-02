package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventsObserver implements EventsManager {

    private Collection<EventProcessor> eventProcessors = new ArrayList<>();

    private Collection<EventProcessor> configureEventProcessors() {
        registerEventProcessor(new LightsEventProcessor());
        registerEventProcessor(new DoorEventProcessor());
        registerEventProcessor(new HallDoorEventProcessor());
        registerEventProcessor(new AlarmEventProcessor(Application.getUserAlarmCode()));
        return eventProcessors;
    }

    @Override
    public void registerEventProcessor(EventProcessor eventProcessor) {
        eventProcessors.add(eventProcessor);
    }

    @Override
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
