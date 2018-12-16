package ru.sbt.mipt.oop.test;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

import ru.sbt.mipt.oop.Components.Alarm.*;

public class AlarmTest {

    @Test
    public void activate() throws Exception {
        AlarmEntity alarm = new AlarmEntity("code");
        alarm.activate("code");
        assertEquals(true, alarm.getBehavior() instanceof ActivatedAlarm);
        assertEquals(AlarmStatus.ACTIVATED, alarm.getStatus());
        System.out.println("test 1 is OK");
    }

    @Test
    public void deactivate() throws Exception {
        AlarmEntity alarm = new AlarmEntity("code");
        alarm.deactivate("code");
        assertEquals(true, alarm.getBehavior() instanceof DeactivatedAlarm);
        assertEquals(AlarmStatus.DEACTIVATED, alarm.getStatus());
        System.out.println("test 2 is OK");
    }

    @Test
    public void alarming() throws Exception {
        AlarmEntity alarm = new AlarmEntity("code");
        alarm.alarming();
        assertEquals(true, alarm.getBehavior() instanceof AlarmedAlarm);
        assertEquals(AlarmStatus.ALARM, alarm.getStatus());
        System.out.println("test 3 is OK");
    }

    @Test
    public void activateWrongCode() throws Exception {
        AlarmEntity alarm = new AlarmEntity("code");
        alarm.activate("wrongCode");
        assertEquals(true, alarm.getBehavior() instanceof AlarmedAlarm);
        assertEquals(AlarmStatus.ALARM, alarm.getStatus());
        System.out.println("test 4 is OK");
    }

}