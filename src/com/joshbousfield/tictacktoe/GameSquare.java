package com.joshbousfield.tictacktoe;

//will need a checking method that is passed the previous direction the piece came from
//will need to use a loop to create the game squares

import java.util.HashMap;
import java.util.Map;
import com.joshbousfield.tictacktoe.PlayPiece.Type;

public class GameSquare {
    private final int xPos;
    private final int yPos;
    private boolean occupied;
    private Type type;
    private Map<String, GameSquare> matchingSquares;

    public GameSquare(int xPos, int yPos, Type type, Map<String, GameSquare> matchingSquares) {
        this.xPos = xPos;
        this.yPos = yPos;
        occupied = false;
        this.type = Type.NONE;
        this.matchingSquares = new HashMap<>();
    }



    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public PlayPiece.Type getType() {
        return type;
    }

    /**
     *
     * @param type a game piece type is passed for the piece being placed on the board, once set occupied
     *             is set to true;
     * @throws IllegalGameMove checks if the GameSquare is occupied, an IllegalGameMove
     * exception is thrown if occupied is set to true
     *
     */
    public void setType(Type type) throws IllegalGameMove {
        if (!occupied) {
            this.type = type;
            occupied = true;
        } else {
            throw new IllegalGameMove("Game Square is already occupied");
        }
    }

    public Map<String, GameSquare> getMatchingSquares() {
        return matchingSquares;
    }

    public void setMatchingSquares(Map<String, GameSquare> matchingSquares) {
        this.matchingSquares = matchingSquares;
    }
}
