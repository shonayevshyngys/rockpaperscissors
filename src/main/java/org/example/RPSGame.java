package org.example;

import org.example.mvc.Injector;
import org.example.mvc.interfaces.ControllerProto;

import java.util.Scanner;

public class RPSGame {

    private final ControllerProto controller;
    private final Scanner scanner;

    public RPSGame(Injector injector) {
        this.controller = injector.getControllerProxy();
        this.scanner = injector.getScanner();
    }

    private static RPSGame instance;

    public static RPSGame getInstance(Injector injector)
    {
        if (instance == null) instance = new RPSGame(injector);
        return instance;
    }

    public void start()
    {

        controller.greetPlayer();
        boolean stop = false;
        while (!stop){
            String s = scanner.nextLine();
            controller.awaitInput(s);
            stop = controller.checkForEnd();
        }
    }
}
