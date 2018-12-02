package ru.sbt.mipt.oop.EventService;

import ru.sbt.mipt.oop.EventService.EventProcessors.EventProcessor;
import ru.sbt.mipt.oop.Components.SmartHome;

public interface EventsManager {
    void registerEventProcessor(EventProcessor eventProcessor);
    void runEventsCycle(SmartHome smartHome);
}
