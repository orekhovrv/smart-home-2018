package ru.sbt.mipt.oop;

public class Alarm {
    private AlarmStatus status;
    private String code = "code";
    private AlarmBehavior behavior;
    private AlarmBehavior deactivatedAlarm;
    private AlarmBehavior alarmedAlarm;
    private AlarmBehavior activatedAlarm;

    public Alarm(String code) {
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

    boolean activate(String code) {
        if (code.equals(this.code)) {
            this.status = AlarmStatus.ACTIVATED;
            behavior = activatedAlarm;
            return true;
        } else {
            this.alarming();
            return false;
        }
    }

    boolean deactivate(String code) {
        if (code.equals(this.code)) {
            this.status = AlarmStatus.DEACTIVATED;
            behavior = deactivatedAlarm;
            return true;
        } else {
            this.alarming();
            return false;
        }
    }

    void alarming() {
        this.status = AlarmStatus.ALARM;
        behavior = alarmedAlarm;
        System.out.println("Alarm is alarming!!!");
    }

    public AlarmStatus getStatus() {
        return status;
    }
}
