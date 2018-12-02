package ru.sbt.mipt.oop.Components.Alarm;

import ru.sbt.mipt.oop.Components.SmartHome;

public abstract class AlarmBehavior {

    public boolean isIgnoringEvents() {
        return false;
    }

    public void gotNotAlarmEvent(SmartHome smartHome) {}

    public void sendSMS() {
        System.out.println("Sending sms");
    }
}
