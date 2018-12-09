package ru.sbt.mipt.oop;


import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.Adapters.Adapter;
import ru.sbt.mipt.oop.EventService.EventsManager;
import ru.sbt.mipt.oop.HomeLoaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.HomeLoaders.SmartHomeLoader;

import java.io.IOException;

@Configuration
public class Config {

    @Bean
    SmartHomeLoader smartHomeLoader() {
        return new FileSmartHomeLoader();
    }

    @Bean
    EventsManager eventsManager() {
        return new Adapter(new SensorEventsManager());
    }


//    @Bean
//    RemoteControlRegistry remoteControlRegistry() {return new RemoteControlRegistry();}
//
//    @Bean
//    SmartHomeRemoteControl programController() throws IOException {
//        return  new SmartHomeRemoteControl();
//    }

}
