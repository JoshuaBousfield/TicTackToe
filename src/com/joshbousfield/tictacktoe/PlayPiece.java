package com.joshbousfield.tictacktoe;

public class PlayPiece {
    public enum Type {
        O, X, NONE
    }
    private final Type type;

    public PlayPiece(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
