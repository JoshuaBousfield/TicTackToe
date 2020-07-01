package com.joshbousfield.tictacktoe;

public class Player {
    private final String name;
    private final PlayPiece playPiece;

    public Player(String name, PlayPiece playPiece) {
        this.name = name;
        this.playPiece = playPiece;
    }

    public String getName() {
        return name;
    }

    public PlayPiece getPlayPiece() {
        return playPiece;
    }
}
