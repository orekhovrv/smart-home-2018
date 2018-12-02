package ru.sbt.mipt.oop.Components.Alarm;

import ru.sbt.mipt.oop.Components.SmartHome;

public class AlarmedAlarm extends AlarmBehavior {

    @Override
    public boolean isIgnoringEvents() {
        return true;
    }

    @Override
    public void gotNotAlarmEvent(SmartHome smartHome) {
        sendSMS();
    }

}
