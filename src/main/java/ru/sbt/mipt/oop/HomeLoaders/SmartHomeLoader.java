package ru.sbt.mipt.oop.HomeLoaders;

import ru.sbt.mipt.oop.Components.SmartHome;

import java.io.IOException;

public interface SmartHomeLoader {
    SmartHome loadSmartHome() throws IOException;
}
