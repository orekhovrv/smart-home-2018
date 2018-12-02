package ru.sbt.mipt.oop;

abstract class AlarmBehavior {

    public boolean isIgnoringEvents() {
        return false;
    }

    void gotNotAlarmEvent(SmartHome smartHome) {}

    void sendSMS() {
        System.out.println("Sending sms");
    }
}
