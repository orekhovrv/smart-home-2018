package ru.sbt.mipt.oop;

public class RandomSensorEventProvider {
    public static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        double nullProb = 0.05;
        double alarmEventProb = 0.2;
        SensorEventType sensorEventType;

        if (Math.random() < nullProb) return null; // null means end of event stream
        if (Math.random() < alarmEventProb) {
            sensorEventType = SensorEventType.values()[(int) (4 + 2* Math.random())];
        } else {
            sensorEventType = SensorEventType.values()[(int) (4* Math.random())];
        }
        String objectId;
        if (sensorEventType.isAlarmEvent()) {
            objectId = "alarm";
        } else {
            objectId = "" + ((int) (10 * Math.random()));
        }
        return new SensorEvent(sensorEventType, objectId);
    }
}
