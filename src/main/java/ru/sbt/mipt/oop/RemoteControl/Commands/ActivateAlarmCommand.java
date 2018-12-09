package ru.sbt.mipt.oop.RemoteControl.Commands;


import ru.sbt.mipt.oop.Components.SmartHome;

public class ActivateAlarmCommand implements Command {
    private final SmartHome smartHome;
    private final String code;

    public ActivateAlarmCommand(SmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.code = password;
    }

    @Override
    public void execute() {
        smartHome.getAlarmEntity().activate(code);
    }
}
