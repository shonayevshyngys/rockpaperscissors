package org.example.mvc;

import org.example.mvc.interfaces.ControllerProto;
import org.example.mvc.interfaces.ModelProto;
import org.example.mvc.interfaces.ViewProto;

public class Controller implements ControllerProto {
    private final ViewProto view;
    private final Model model;


    public Controller(ViewProto view, ModelProto model) {
        this.view = view;
        this.model = (Model) model;
    }

    @Override
    public String awaitInput(String s)
    {
        // this is the main method, the main logic in proxy class
        return "pluck";
    }
    @Override
    public String greetPlayer()
    {
        String s = """
                Ciao, player
                Today we play in Rock paper scissor
                Before we play I recommend you to read manual
                To read the manual please type 'help'""";
        view.print(s);
        return s;
    }

    @Override
    public String start() {
        if (model.isGameStarted()) {
            return "Sorry, but game is already started";
        }
        else {
            model.start();
            return "Game is started";
        }

    }

    @Override
    public String play(String s) {
        return model.play(s);
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public String amount(String s) {
        int n;
        try {
            n = Integer.parseInt(s);
        } catch (final NumberFormatException e) {
            return "Wrong format for a number";
        }
        return model.amount(n);
    }

    @Override
    public boolean checkForEnd() {
        return model.checkForEnd();
    }


    @Override
    public String help()
    {
        return model.getInfo();
    }



}
