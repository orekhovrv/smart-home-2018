package ru.sbt.mipt.oop.Components.Alarm;

import ru.sbt.mipt.oop.Components.SmartHome;

public class ActivatedAlarm extends AlarmBehavior {


    @Override
    public void gotNotAlarmEvent(SmartHome smartHome) {
        smartHome.getAlarmEntity().alarming();
        sendSMS();
    }
}
