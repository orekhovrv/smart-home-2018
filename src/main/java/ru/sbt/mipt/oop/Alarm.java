package ru.sbt.mipt.oop;

public class Alarm {
    private AlarmStatus status;
    private String code = "code";
    AlarmBehavior behavior;
    private AlarmBehavior activatedAlarm = new ActivatedAlarm();
    private AlarmBehavior deactivatedAlarm = new DeactivatedAlarm();
    private AlarmBehavior alarmedAlarm = new AlarmedAlarm();

    boolean activate(String code) {
        if (code.equals(this.code)) {
            this.status = AlarmStatus.ACTIVATED;
            behavior = activatedAlarm;
            return true;
        } else {
            this.alarming(code);
            return false;
        }
    }

    boolean deactivate(String code) {
        if (code.equals(this.code)) {
            this.status = AlarmStatus.DEACTIVATED;
            behavior = deactivatedAlarm;
            return true;
        } else {
            this.alarming(code);
            return false;
        }
    }

    boolean alarming(String code) {
        this.status = AlarmStatus.ALARM;
        behavior = alarmedAlarm;
        if (code.equals(this.code)) {
            return true;
        } else {
            return false;
        }
    }

    public AlarmStatus getStatus() {
        return status;
    }
}
