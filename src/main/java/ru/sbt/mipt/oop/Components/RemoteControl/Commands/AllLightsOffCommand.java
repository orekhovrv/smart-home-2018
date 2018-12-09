package ru.sbt.mipt.oop.Components.RemoteControl.Commands;


import ru.sbt.mipt.oop.Components.SmartHome;

public class AllLightsOffCommand implements Command {

    private final SmartHome smartHome;

    public AllLightsOffCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void execute() {
        smartHome.turnLights(false);
    }
}
