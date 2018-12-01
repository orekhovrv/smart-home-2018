package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (isAlarmEvent(event)) {
            if (event.getType() == ALARM_DEACTIVATE) {
                smartHome.alarm.deactivate("code");
            }
            if (event.getType() == ALARM_ACTIVATE) {
                smartHome.alarm.activate("code");
            }
        } else {
            smartHome.alarm.behavior.gotNotAlarmEvent(smartHome);
        }

    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == ALARM_DEACTIVATE || event.getType() == ALARM_ACTIVATE ;
    }
}
