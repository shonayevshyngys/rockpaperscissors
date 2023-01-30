package org.example.mvc;

import org.example.mvc.interfaces.ModelProto;
import org.example.util.FigureEnum;

import java.util.Random;

public class Model implements ModelProto {

    private int amountOfGames = -1;
    private boolean gameStarted = false;
    private final Random r;

    private int amountPlayed;

    public Model() {
        r = new Random();
    }

    @Override
    public String getInfo()
    {
        return """
                help - shows all commands
                start - to start the game
                amount [] - to set amount of games, e.g. 'amount 3' set amount of games to 3
                play [] - to choose a figure to play against pc, e.g 'play rock' or 'play paper'
                exit - to terminate program""";
    }

    public void start()
    {
        gameStarted = true;
        if (amountOfGames == 0) amountOfGames = 5;
    }

    @Override
    public String play(String s) {
        if (!gameStarted) return "Game isn't started, type 'start' to start the game";
        FigureEnum playerFigure;
        try {
            playerFigure = FigureEnum.valueOf(s);
        }
        catch (IllegalArgumentException e) {
            return "No such figure exists";
        }

        FigureEnum computerFigure = FigureEnum.values()[r.nextInt(FigureEnum.values().length)];
        amountPlayed++;
        return checkIfBeats(playerFigure, computerFigure);
    }

    @Override
    public String amount(int n) {
        if (!gameStarted && n > 0) {
            amountOfGames = n;
            return "Amount of games set to " + n;
        }
        else return "Sorry, the game either already started or you've put wrong amount. Default amount of games is 5";
    }

    private String checkIfBeats(FigureEnum who, FigureEnum whom)
    {
        if (who.equals(whom)) return "We both choose the same figure haha";
        if (who.beats.equals(whom)) return "you win " + who +" beats "+whom;
        return "you lose " + who + " loses to " + whom;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean checkForEnd(){
        return amountOfGames == amountPlayed;
    }
}
