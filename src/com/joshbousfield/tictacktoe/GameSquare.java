package com.joshbousfield.tictacktoe;

//will need a checking method that is passed the previous direction the piece came from
//will need to use a loop to create the game squares

import java.util.HashMap;
import java.util.Map;
import com.joshbousfield.tictacktoe.PlayPiece.Type;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameSquare {
    private final int xPos;
    private final int yPos;
    private final CoOrd coOrd;
    private boolean occupied;
    private ImageView imageView;
    private Type type;

    public GameSquare(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.coOrd = new CoOrd(xPos, yPos);
        occupied = false;
        this.imageView = new ImageView(new Image(getClass().getResource("images/gamesquare.png").toExternalForm()));
        this.type = Type.NONE;
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
            switch (type) {
                case O:
                    this.imageView.setImage(new Image(getClass().getResource("images/gamesquare_o.png").toExternalForm()));
                    break;
                case X:
                    this.imageView.setImage(new Image(getClass().getResource("images/gamesquare_x.png").toExternalForm()));
                    break;
            }
        } else {
            throw new IllegalGameMove("Game Square is already occupied");
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public CoOrd getCoOrd() {
        return coOrd;
    }

    public static class CoOrd {
        int x;
        int y;

        public CoOrd(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return Integer.toString(getX()) + getY();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
