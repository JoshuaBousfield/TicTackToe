package com.joshbousfield.tictacktoe;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {
    @FXML public GridPane gameSquares;
    //needs setters
    private int gridX;
    private int gridY;
    private int winCondition;
    private final Map<String, GameSquare> squares = new HashMap<>();
    private final List<Combination> combinations = new ArrayList<>();
    private boolean playersTurn;
    private Player player;

    public void initialize() {
        gridY = 3;
        gridX = 3;
        winCondition = 3;
        playersTurn = true;
        player = new Player("Josh", new PlayPiece(PlayPiece.Type.X));
        setBoard(gridX, gridY);
        setGrid();
        findCombinations();
    }

    private void setBoard(int xRow, int yRow) {
        for(int x=0; x<xRow; x++) {
            for(int y=0; y<yRow; y++) {
                GameSquare square = new GameSquare(x, y);
                squares.put(square.getCoOrd().toString(), square);
            }
        }
    }

    private void findCombinations() {
        for (int x=0; x<gridX; x++) {
            //finds x axis
            for (int y = 0; y < gridY; y++) {
                //finds y axis
                //createCombinations
                createCombination("x+ y-", x, y);
                createCombination("x+ y", x, y);
                createCombination("x+ y+", x, y);
                createCombination("x y+", x, y);
            }
        }
    }

    private boolean checkMatch(int x, int y) {
        return squares.get(new GameSquare.CoOrd(x, y).toString()) != null;
    }

    /**
     * Creates a list of possible game winning combinations, which is then checked
     * if it is a valid combination based off the winCondition
     *
     * @param modifier a string is passed to tell the method
     *                 which direction to check for matches
     * @param x the x position of the GameSquare being check
     * @param y the y position of the GameSquare being check
     */
    private void createCombination(String modifier, int x, int y) {
        List<GameSquare> newCombination = new ArrayList<>();
        for (int w=0; w<winCondition; w++) {
            //checks next squares
            switch (modifier) {
                case "x+ y-":
                    if (checkMatch(x + w, y - w)) {
                        newCombination.add(squares.get(new GameSquare.CoOrd(x + w, y - w).toString()));
                    }
                    break;
                case "x+ y":
                    if (checkMatch(x + w, y)) {
                        newCombination.add(squares.get(new GameSquare.CoOrd(x + w, y).toString()));
                    }
                    break;
                case "x+ y+":
                    if (checkMatch(x + w, y + w)) {
                        newCombination.add(squares.get(new GameSquare.CoOrd(x + w, y + w).toString()));
                    }
                    break;
                case "x y+":
                    if (checkMatch(x, y + w)) {
                        newCombination.add(squares.get(new GameSquare.CoOrd(x, y + w).toString()));
                    }
                    break;
            }
        }
        if (newCombination.size() == winCondition) {
            //check if combo exists
            Combination combo = new Combination(newCombination);
            combinations.add(combo);
        }
    }

    private Combination findWinningCombination() {
        for (Combination combo : combinations) {
            PlayPiece.Type type = combo.checkWin();
            if (!type.equals(PlayPiece.Type.NONE)) {
                System.out.println("winning type: " + type.toString());
                return combo;
            }
        }
        return null;
    }

    private void setGrid() {
        for (Map.Entry<String, GameSquare> square : squares.entrySet()) {
            square.getValue().getImageView().setOnMouseClicked(mouseEvent -> placePiece(square.getValue()));
            gameSquares.add(square.getValue().getImageView(), square.getValue().getCoOrd().getX(),
                    square.getValue().getCoOrd().getY());
        }
    }

    private void placePiece(GameSquare square) {
        if (playersTurn) {
            try {
                square.setType(player.getPlayPiece().getType());
                findWinningCombination();
//                playersTurn = false;
            } catch (IllegalGameMove e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
