package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.Adapters.Adapter;
import ru.sbt.mipt.oop.Components.Alarm.AlarmEntity;
import ru.sbt.mipt.oop.Components.SmartHome;
import ru.sbt.mipt.oop.EventService.EventsManager;
import ru.sbt.mipt.oop.HomeLoaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.HomeLoaders.SmartHomeLoader;

import java.io.IOException;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);
    public static String getUserAlarmCode() { return "code"; }

//    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
////    private  static HomeEventsObserver homeEventsObserver = new HomeEventsObserver();
//    private static EventsManager eventsManager = new Adapter(new SensorEventsManager());
//
//    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
//        Application.smartHomeLoader = smartHomeLoader;
//    }

    public static void main(String... args) throws IOException {
        logger.info("Starting configuration...");
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        // получение бинов
        SmartHomeLoader smartHomeLoader = context.getBean(SmartHomeLoader.class);
        EventsManager eventsManager = context.getBean(EventsManager.class);

        // Загрузка дома
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setAlarmEntity(new AlarmEntity(getUserAlarmCode()));

        //Запуск
        eventsManager.runEventsCycle(smartHome);
    }





}
