package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor {

    public AlarmEventProcessor(String userAlarmCode) { this.userAlarmCode = userAlarmCode; }

    private String userAlarmCode;

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType().isAlarmEvent()) {
            if (event.getType() == ALARM_DEACTIVATE) {
                boolean wasDeactivated = smartHome.getAlarm().deactivate(userAlarmCode);
                if (wasDeactivated) {
                    System.out.println("Alarm was deactivated");
                } else {
                    System.out.println("Alarm was alarmed. Code is wrong");
                }
            }
            if (event.getType() == ALARM_ACTIVATE) {
                boolean wasActivated = smartHome.getAlarm().activate(userAlarmCode);
                if (wasActivated) {
                    System.out.println("Alarm was activated");
                } else {
                    System.out.println("Alarm was alarmed. Code is wrong");
                }
            }
        } else {
            smartHome.getAlarm().getBehavior().gotNotAlarmEvent(smartHome);
        }

    }

}
