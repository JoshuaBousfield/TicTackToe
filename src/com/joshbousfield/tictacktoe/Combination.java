package com.joshbousfield.tictacktoe;

import java.util.ArrayList;
import java.util.List;
import com.joshbousfield.tictacktoe.PlayPiece.Type;

public class Combination {
    private List<GameSquare> combinations;
    private String winningType;

    public Combination(List<GameSquare> squares) {
        this.combinations = new ArrayList<>();
        this.combinations.addAll(squares);
        this.winningType = null;
    }

    public String checkWin() {
        Type previousType = Type.NONE;
        for (GameSquare square : combinations) {
            if (square.getType().equals(PlayPiece.Type.NONE)) {
                return null;
            } else if (previousType.equals(Type.NONE)) {
                previousType = square.getType();
            }
            if (!square.getType().equals(previousType)) {
                return null;
            }
        }
        System.out.println("win found");
        printList();
        winningType = previousType.name();
        return winningType;
    }

    public List<GameSquare> getCombinations() {
        return combinations;
    }

    public void printList() {
        for (GameSquare square : combinations) {
            System.out.print(square.getxPos());
            System.out.println(square.getyPos());
        }
        System.out.println("");
    }

    public String getWinningType() {
        return winningType;
    }
}
