package ru.sbt.mipt.oop.Components.Alarm;

import ru.sbt.mipt.oop.Components.SmartHome;

public abstract class AlarmBehavior {

    public void sendSMS() {
        System.out.println("Sending sms");
    }

    public  boolean activate(String code) { return false; }

    public  boolean deactivate(String code) { return false; }
}
