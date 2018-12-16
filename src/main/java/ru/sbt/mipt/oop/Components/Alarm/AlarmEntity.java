package ru.sbt.mipt.oop.Components.Alarm;

import ru.sbt.mipt.oop.Components.SmartHome;

public class AlarmEntity {
    private AlarmStatus status;
    private String code = "code";
    private AlarmBehavior behavior;
    private AlarmBehavior deactivatedAlarm;
    private AlarmBehavior alarmedAlarm;
    private AlarmBehavior activatedAlarm;

    public AlarmEntity(String code) {
        this.code = code;
        this.deactivatedAlarm = new DeactivatedAlarm();
        this.alarmedAlarm = new AlarmedAlarm();
        this.activatedAlarm = new ActivatedAlarm();
        this.behavior = deactivatedAlarm;
        this.status = AlarmStatus.DEACTIVATED;
    }

    public AlarmBehavior getBehavior() {
        return behavior;
    }

    public boolean activate(String code) {
        if (code.equals(this.code)) {
            this.status = AlarmStatus.ACTIVATED;
            behavior = activatedAlarm;
            return true;
        } else {
            this.alarming();
            return false;
        }
    }

    public boolean deactivate(String code) {
        if (code.equals(this.code)) {
            this.status = AlarmStatus.DEACTIVATED;
            behavior = deactivatedAlarm;
            return true;
        } else {
            this.alarming();
            return false;
        }
    }

    public void alarming() {
        this.status = AlarmStatus.ALARM;
        behavior = alarmedAlarm;
        System.out.println("AlarmEntity is alarming!!!");
    }

    public AlarmStatus getStatus() {
        return status;
    }

    public void gotNotAlarmEvent(SmartHome smartHome) { behavior.gotNotAlarmEvent(smartHome); }

    public boolean isIgnoringEvents() { return behavior.isIgnoringEvents(); }

    public void sendSMS() { behavior.sendSMS(); }
}
