package org.example.mvc.interfaces;

public interface ControllerProto {

    String awaitInput(String s);
    String help();
    String greetPlayer();

    String start();

    String play(String s);

    void exit();

    String amount(String s);

    boolean checkForEnd();
}
