package com.team8.backend.ninemanmorris.moves;

import java.util.ArrayList;
import java.util.List;

import com.team8.backend.ninemanmorris.Board;
import com.team8.backend.ninemanmorris.Phase;
import com.team8.backend.ninemanmorris.Player;
import com.team8.backend.ninemanmorris.Position;
import com.team8.backend.ninemanmorris.PublicPosition;
import com.team8.backend.ninemanmorris.Token;

/**
 * Class for modularising the Move function
 */
public class Move {
    // Failed by default
    protected boolean moveStatus = false;
    // Variables for using
    protected Board board;
    protected Player player;
    // Coordinate Variables
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;

    /**
     * Constructor for the Move Class, makes a move based on the input
     * 
     * @param fromRow - Coordinate of the from row
     * @param fromCol - Coodinate f the from col
     * @param toRow   - Coordinate of the to Row
     * @param toCol   - Coordinate of the to Col
     * @param player  - Player instance; curr player if the board
     * @param board   - board being intercated with
     */
    public Move(int fromRow, int fromCol, int toRow, int toCol, Player player, Board board) {
        // Setting variables for use in functions
        this.board = board;
        this.player = player;
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    /**
     * CHecks for whether a given position is in a mill
     * 
     * @param position
     * @return
     */
    public boolean positionInMill(PublicPosition position) {
        boolean inMill = false;

        for (Position neighbour : position.getNeighbours()) {
            if (neighbour.getToken() == position.getToken()) {

            }
        }

        return inMill;
    }

    /**
     * returns if the publick Position is empty
     * 
     * @param position
     * @return
     */
    protected boolean publicPositionEmpty(PublicPosition position) {
        return position.getToken() == Token.TILE;
    }

    /**
     * Getter for movestatus
     * 
     * @return true false depending o nif move was sussessful
     */
    public boolean getMoveStatus() {
        return this.moveStatus;
    }
}
