package ru.sbt.mipt.oop.Components.Alarm;


public class AlarmedAlarm extends AlarmBehavior {

    private AlarmEntity alarm;
    private String code;

    public AlarmedAlarm(AlarmEntity alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public boolean deactivate(String code) {
        if (code.equals(this.code)) {
            alarm.setStatus(AlarmStatus.DEACTIVATED);
            alarm.setBehavior(new DeactivatedAlarm(alarm ,code));
            return true;
        } else {
            return false;
        }
    }
}
