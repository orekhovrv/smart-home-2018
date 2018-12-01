package ru.sbt.mipt.oop;

public class ActivatedAlarm extends AlarmBehavior {

    @Override
    public void gotNotAlarmEvent(SmartHome smartHome) {
        smartHome.alarm.alarming("code");
        sendSMS();
    }
}
