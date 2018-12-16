package ru.sbt.mipt.oop.Components.Alarm;

public class DeactivatedAlarm extends AlarmBehavior {

    private AlarmEntity alarm;
    private String code;

    public DeactivatedAlarm(AlarmEntity alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public boolean activate(String code) {
        if (code.equals(this.code)) {
            alarm.setStatus(AlarmStatus.ACTIVATED);
            alarm.setBehavior(new ActivatedAlarm(alarm ,code));
            return true;
        } else {
            alarm.alarming();
            return false;
        }
    }
}
