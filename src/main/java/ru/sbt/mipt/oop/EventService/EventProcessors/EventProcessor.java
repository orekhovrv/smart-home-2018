package ru.sbt.mipt.oop.EventService.EventProcessors;

import ru.sbt.mipt.oop.EventService.SensorEvent;
import ru.sbt.mipt.oop.Components.SmartHome;

public interface EventProcessor {

    void processEvent(SmartHome smartHome, SensorEvent event);

}
