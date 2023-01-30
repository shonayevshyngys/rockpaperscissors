package org.example;

import org.example.mvc.Injector;
import org.example.util.FigureEnum;


public class Main {
    public static void main(String[] args) {

        Injector injector = new Injector();
        RPSGame.getInstance(injector).start();
    }
}