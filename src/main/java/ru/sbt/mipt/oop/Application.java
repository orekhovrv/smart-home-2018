package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.Adapters.Adapter;

import java.io.IOException;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
//    private  static HomeEventsObserver homeEventsObserver = new HomeEventsObserver();
    private static EventsManager eventsManager = new Adapter(new SensorEventsManager());

    public static String getUserAlarmCode() { return "code"; }

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setAlarm(new Alarm(getUserAlarmCode()));
        eventsManager.runEventsCycle(smartHome);
    }





}
