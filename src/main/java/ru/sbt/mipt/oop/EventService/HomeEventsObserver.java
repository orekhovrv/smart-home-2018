package ru.sbt.mipt.oop.EventService;

import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.EventService.EventProcessors.*;
import ru.sbt.mipt.oop.Components.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventsObserver implements EventsManager {

    private Collection<EventProcessor> eventProcessors = new ArrayList<>();

    private Collection<EventProcessor> configureEventProcessors() {
        registerEventProcessor(new AlarmEventProcessorDecorator(new LightsEventProcessor()));
        registerEventProcessor(new AlarmEventProcessorDecorator(new DoorEventProcessor()));
        registerEventProcessor(new AlarmEventProcessorDecorator(new HallDoorEventProcessor()));
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
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(smartHome, event);
            }
            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }
}
