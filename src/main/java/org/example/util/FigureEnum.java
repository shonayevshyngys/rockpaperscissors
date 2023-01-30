package org.example.util;

public enum FigureEnum {
    rock,
    paper,
    scissors;

    public FigureEnum beats;


    public FigureEnum getBeats() {
        return beats;
    }

    public void setBeats(FigureEnum beats) {
        this.beats = beats;
    }
}
