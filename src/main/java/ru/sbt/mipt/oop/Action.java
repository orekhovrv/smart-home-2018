package ru.sbt.mipt.oop;

@ FunctionalInterface
public interface Action {
    void executeAction(Executable object); //Выполняет лямбду от object
}
