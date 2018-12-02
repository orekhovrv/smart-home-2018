package ru.sbt.mipt.oop;

public enum SensorEventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED, ALARM_ACTIVATE, ALARM_DEACTIVATE;

    public boolean isAlarmEvent() {
        return this.equals(ALARM_DEACTIVATE)  || this.equals(ALARM_ACTIVATE) ;
    }
}
