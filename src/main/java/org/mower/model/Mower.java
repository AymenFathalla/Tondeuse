package org.mower.model;

public class Mower {
    private Position position;
    private char orientation;

    public Mower(Position position, char orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }
}
