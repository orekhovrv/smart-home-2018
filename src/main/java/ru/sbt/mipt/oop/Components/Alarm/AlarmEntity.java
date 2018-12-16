package ru.sbt.mipt.oop.Components.Alarm;

import ru.sbt.mipt.oop.Components.SmartHome;

public class AlarmEntity {
    private AlarmStatus status;
    private String code = "code";
    private AlarmBehavior behavior;


    public AlarmEntity(String code) {
        this.code = code;
        this.behavior = new DeactivatedAlarm(this, code);
        this.status = AlarmStatus.DEACTIVATED;
    }

    public AlarmBehavior getBehavior() {
        return behavior;
    }

    public boolean activate(String code) {
        return behavior.activate(code);
    }

    public boolean deactivate(String code) {
        return behavior.activate(code);
    }

    public void alarming() {
        this.status = AlarmStatus.ALARM;
        behavior = new AlarmedAlarm(this, code);
        System.out.println("AlarmEntity is alarming!!!");
    }

    public AlarmStatus getStatus() {
        return status;
    }

    public void sendSMS() { behavior.sendSMS(); }

    public boolean isActivated() { return getStatus().equals(AlarmStatus.ACTIVATED) || getStatus().equals(AlarmStatus.ALARM); }

    public void setStatus(AlarmStatus status) {
        this.status = status;
    }

    public void setBehavior(AlarmBehavior behavior) {
        this.behavior = behavior;
    }
}
