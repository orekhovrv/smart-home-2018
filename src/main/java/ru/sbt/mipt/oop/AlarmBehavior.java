package ru.sbt.mipt.oop;

abstract class AlarmBehavior {

    void gotNotAlarmEvent(SmartHome smartHome) {}

    void sendSMS() {
        System.out.println("Sending sms");
    }
}
