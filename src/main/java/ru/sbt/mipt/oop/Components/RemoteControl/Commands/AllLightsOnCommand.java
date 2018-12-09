package ru.sbt.mipt.oop.Components.RemoteControl.Commands;


import ru.sbt.mipt.oop.Components.SmartHome;

public class AllLightsOnCommand implements Command {

    private final SmartHome smartHome;

    public AllLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void execute() {
        smartHome.turnLights(true);
    }
}
