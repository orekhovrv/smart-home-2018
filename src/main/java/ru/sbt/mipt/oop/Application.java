package ru.sbt.mipt.oop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.Components.Alarm.AlarmEntity;
import ru.sbt.mipt.oop.Components.SmartHome;
import ru.sbt.mipt.oop.EventService.EventProcessors.*;
import ru.sbt.mipt.oop.EventService.EventsManager;
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

        // Создание процессоров
        eventsManager.registerEventProcessor(new AlarmEventProcessorDecorator(new LightsEventProcessor()));
        eventsManager.registerEventProcessor(new AlarmEventProcessorDecorator(new DoorEventProcessor()));
        eventsManager.registerEventProcessor(new AlarmEventProcessorDecorator(new HallDoorEventProcessor()));

        //Запуск
        eventsManager.runEventsCycle(smartHome);
    }





}
