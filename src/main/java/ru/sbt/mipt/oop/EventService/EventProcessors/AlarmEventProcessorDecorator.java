package ru.sbt.mipt.oop.EventService.EventProcessors;

import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.EventService.SensorEvent;
import ru.sbt.mipt.oop.Components.SmartHome;

import static ru.sbt.mipt.oop.EventService.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.EventService.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessorDecorator implements EventProcessor {

    private String userAlarmCode;
    private EventProcessor wrapeeProcessor;

    public AlarmEventProcessorDecorator(EventProcessor wrapeeProcessor) {
        this.userAlarmCode = Application.getUserAlarmCode();
        this.wrapeeProcessor = wrapeeProcessor;
    }


    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (smartHome.getAlarmEntity() != null && event.getType().isAlarmEvent()) {
            if (event.getType() == ALARM_DEACTIVATE) {
                boolean wasDeactivated = smartHome.getAlarmEntity().deactivate(userAlarmCode);
                if (wasDeactivated) {
                    System.out.println("AlarmEntity was deactivated");
                } else {
                    System.out.println("AlarmEntity was alarmed. Code is wrong");
                }
            }
            if (event.getType() == ALARM_ACTIVATE) {
                boolean wasActivated = smartHome.getAlarmEntity().activate(userAlarmCode);
                if (wasActivated) {
                    System.out.println("AlarmEntity was activated");
                } else {
                    System.out.println("AlarmEntity was alarmed. Code is wrong");
                }
            }
        } else {
            if (smartHome.getAlarmEntity() != null && smartHome.getAlarmEntity().isActivated()) {
                smartHome.getAlarmEntity().alarming();
                smartHome.getAlarmEntity().sendSMS();
            } else {
                wrapeeProcessor.processEvent(smartHome, event);
            }
        }
    }

}
