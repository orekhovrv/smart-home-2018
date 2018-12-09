package ru.sbt.mipt.oop.RemoteControl.Commands;


import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.SmartHome;

public class TurnHallLightOnCommand implements Command {

    private final SmartHome smartHome;

    public TurnHallLightOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if ("hall".equals(light.getRoomName())) {
                    light.setOn(true);
                }
            }
        });
    }
}
