package com.joshbousfield.tictacktoe;

public class IllegalGameMove extends Exception {
    public IllegalGameMove(String message) {
        super(message);
    }
}
