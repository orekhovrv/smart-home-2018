package ru.sbt.mipt.oop.HomeLoaders;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.Components.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader {
    @Override
    public SmartHome loadSmartHome() throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("output.js")));
        return gson.fromJson(json, SmartHome.class);
    }
}
