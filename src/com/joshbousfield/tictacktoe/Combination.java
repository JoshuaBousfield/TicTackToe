package com.joshbousfield.tictacktoe;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    private List<GameSquare> combinations;

    public Combination(List<GameSquare> squares) {
        this.combinations = new ArrayList<>();
        this.combinations.addAll(squares);
    }

    //add method to check for win loop through and check that type is all the same and != NONE

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
