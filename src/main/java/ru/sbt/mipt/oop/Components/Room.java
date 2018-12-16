package ru.sbt.mipt.oop.Components;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Executable;

import java.util.Collection;

public class Room implements Executable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public Light getLightById(String objectId) {
        for (Light light : lights) {
            if (objectId.equals(light.getId())) return light;
        }
        return null;
    }

    public Door getDoorById(String objectId) {
        for (Door door : doors) {
            if (objectId.equals(door.getId())) return door;
        }
        return null;
    }


    @Override
    public void execute(Action action) {
        action.executeAction(this);
        for (Door door : this.getDoors())
            door.execute(action);
        for (Light light : this.getLights())
            light.execute(action);
    }

}


