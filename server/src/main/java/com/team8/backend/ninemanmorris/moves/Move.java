package com.team8.backend.ninemanmorris.moves;

import java.util.ArrayList;

import com.team8.backend.ninemanmorris.enums.Token;
import com.team8.backend.ninemanmorris.game.Board;
import com.team8.backend.ninemanmorris.game.Player;
import com.team8.backend.ninemanmorris.positions.Position;
import com.team8.backend.ninemanmorris.positions.PublicPosition;

/**
 * Class for modularising the Move function
 */
public abstract class Move {
    // Failed by default
    protected boolean moveStatus = false;
    protected boolean gameStatus = false;
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

    /***
     * Checks for neighbours of the current position, and
     * determines if it is the same piece, followed by updating
     * a counter based on the number of similar. This is followed
     * by looping throug hthe neighbours of the similar position
     * and repeating the process. By the end, if the row or col count
     * are equal to 3 or more, a mill exist at that position
     * 
     * @param position - PublicPosition of the position being checked for
     * @return - boolean value on whether a mill exist at the position
     */
    public boolean checkMillPosition(PublicPosition position) { // remember

        // Obtained for easy access
        int rowIndex = position.getRowIndex();
        int colIndex = position.getColIndex();

        // Counter of positions with the required index
        int rowCount = 1;
        int colCount = 1;

        // Store all
        ArrayList<PublicPosition> temporaryPositions = new ArrayList<PublicPosition>();

        for (Position temporaryPosition : position.getNeighbours()) {
            if (temporaryPosition.getToken() == position.getToken()) {
                // Add into the array after finding from Public Positions
                for (PublicPosition temporaryPublic : board.getPublicPositions()) {
                    if (temporaryPublic.getRowIndex() == temporaryPosition.getRowIndex()
                            & temporaryPublic.getColIndex() == temporaryPosition.getColIndex()) {
                        // adds to it
                        temporaryPositions.add(temporaryPublic);
                    }
                }
                // Checks for row and increments the count
                if (temporaryPosition.getRowIndex() == rowIndex) {
                    rowCount++;
                } else if (temporaryPosition.getColIndex() == colIndex) {
                    colCount++;
                }
            }
        }

        // Iteration over the neighbour
        for (PublicPosition temporaryPublicPosition : temporaryPositions) {
            for (Position temporaryPosition : temporaryPublicPosition.getNeighbours()) {
                if (temporaryPosition.getToken() == position.getToken()) {
                    // Checks for row and increments the count
                    if (temporaryPosition.getRowIndex() == rowIndex && temporaryPosition.getColIndex() == colIndex) {
                        // pass
                    } else if (temporaryPosition.getRowIndex() == rowIndex) {
                        rowCount++;
                    } else if (temporaryPosition.getColIndex() == colIndex) {
                        colCount++;
                    }
                }
            }
        }

        return rowCount >= 3 || colCount >= 3;
    }
}
