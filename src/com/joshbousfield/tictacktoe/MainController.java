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
    private int gridX;
    private int gridY;
    private int winCondition;
    private int movesPlayed;
    private final Map<String, GameSquare> squares = new HashMap<>();
    private final List<Combination> combinations = new ArrayList<>();
    private Player currentPlayersMove;
    private Player player1;
    private Player player2;

    //TODO check for a draw
    public void initialize() {
        gridY = 3;
        gridX = 3;
        winCondition = 3;
        player1 = new Player("player1", new PlayPiece(PlayPiece.Type.X));
        player2 = new Player("player2", new PlayPiece(PlayPiece.Type.O));
        currentPlayersMove = player1;
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
            String type = combo.checkWin();
            if (type != null) {
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
        try {
            square.setType(currentPlayersMove.getPlayPiece().getType());
            Combination combination = findWinningCombination();
            movesPlayed++;
            if (combination != null) {
                System.out.println("winning type" + combination.getWinningType());
            } else if (movesPlayed == (gridX * gridY)) {
                System.out.println("Draw");
            }
            movePlayed();

        } catch (IllegalGameMove e) {
            System.out.println(e.getMessage());
        }
    }

    private void movePlayed() {
        switch (currentPlayersMove.getName()) {
            case "player1": this.currentPlayersMove = player2; break;
            case "player2": this.currentPlayersMove = player1; break;
        }
    }
}
