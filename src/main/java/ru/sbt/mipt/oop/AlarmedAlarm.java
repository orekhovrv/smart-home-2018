package ru.sbt.mipt.oop;

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
