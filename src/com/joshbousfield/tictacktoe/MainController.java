package com.joshbousfield.tictacktoe;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    @FXML public GridPane gameSquares;
    private final Map<GameSquare.CoOrd, GameSquare> squares = new HashMap<>();


    public void initialize() {
        setBoard(3, 3);
        setGrid();
    }

    private void setBoard(int xRow, int yRow) {
        GridPane gridPane = new GridPane();
        for(int x=0; x<xRow; x++) {
            for(int y=0; y<yRow; y++) {
                GameSquare square = new GameSquare(x, y);
                System.out.println(square.getCoOrd().toString());
                squares.put(square.getCoOrd(), square);
            }
        }
    }

    private void setGrid() {
        for (Map.Entry<GameSquare.CoOrd, GameSquare> square : squares.entrySet()) {
            ImageView view = new ImageView();
            view.setImage(square.getValue().getTile());
            gameSquares.add(view, square.getKey().getX(), square.getKey().getY());
        }
    }
}
