package ru.sbt.mipt.oop.test;

import org.testng.annotations.Test;
import ru.sbt.mipt.oop.Components.SmartHome;
import ru.sbt.mipt.oop.EventService.HomeEventsObserver;

import static org.junit.Assert.assertTrue;

public class HomeEventsObserverTest {

    HomeEventsObserver observer;

    @Test
    public void test() {
        observer = new HomeEventsObserver();
        observer.registerEventProcessor((smartHome, event) -> {
            assertTrue(true);
            System.out.println("event is processed");
        });
        observer.runEventsCycle(new SmartHome());
    }
}
