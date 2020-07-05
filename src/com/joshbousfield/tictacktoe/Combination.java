package com.joshbousfield.tictacktoe;

import java.util.ArrayList;
import java.util.List;
import com.joshbousfield.tictacktoe.PlayPiece.Type;

public class Combination {
    private List<GameSquare> combinations;

    public Combination(List<GameSquare> squares) {
        this.combinations = new ArrayList<>();
        this.combinations.addAll(squares);
    }

    //add method to check for win loop through and check that type is all the same and != NONE
    public Type checkWin() {
        Type previousType = Type.NONE;
        for (GameSquare square : combinations) {
            if (square.getType().equals(PlayPiece.Type.NONE)) {
                return Type.NONE;
            } else if (previousType.equals(Type.NONE)) {
                previousType = square.getType();
            }
            if (!square.getType().equals(previousType)) {
                return Type.NONE;
            }
        }
        System.out.println("win found");
        printList();
        return previousType;
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
}
