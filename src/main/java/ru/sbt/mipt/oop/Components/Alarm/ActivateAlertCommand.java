package ru.sbt.mipt.oop.Components.Alarm;

import ru.sbt.mipt.oop.Components.SmartHome;
import ru.sbt.mipt.oop.Components.RemoteControl.Commands.Command;


public class ActivateAlertCommand implements Command {

    private final SmartHome smartHome;
    private final String code;

    public ActivateAlertCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getAlarmEntity().alarming();
    }
}
