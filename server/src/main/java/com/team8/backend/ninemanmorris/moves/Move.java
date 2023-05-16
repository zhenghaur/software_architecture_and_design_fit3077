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

    private bool checkValidMove(int token){
        ArrayList<PublicPosition> publicPositions = this.board.getPublicPositions();
        int count = 0
        boolean flag = false;
        int i = 0
        while (!flag && i < publicPositions.length()){
            if (publicPositions.get(i).getToken() == token) {
                count++;
                for (PublicPosition neighbor :  position.getNeighbours) {
                    // Obtains the position at the given index
                    if (neighbor.canEnter()) {
                            flag = true;
                        }
                    }
                } 
            }
            i++;
        }
        return (count < 3) || flag;
        
    }

    private bool checkMillPosition(int row, int col) {
        ArrayList<PublicPosition> publicPositions = this.board.getPublicPositions();
        int token = this.board.getTokenAtCoord(row, col);
        boolean rowFlag = true;
        boolean colFlag = true;
        if (row != 3){
            for (PublicPosition position : publicPositions) {
                // Obtains the position at the given index
                if (position.getRowIndex() == row && position.getToken() != token) {
                        rowFlag = false;
                    }
                }
            }
        } else {
            if (col < 3){
                for (PublicPosition position : publicPositions) {
                    // Obtains the position at the given index
                    if (position.getRowIndex() == row && position.getToken() != token && position.getColIndex < 3) {
                            rowFlag = false;
                        }
                    }
                }
            } else {
                for (PublicPosition position : publicPositions) {
                    // Obtains the position at the given index
                    if (position.getRowIndex() == row && position.getToken() != token && position.getColIndex > 3) {
                            rowFlag = false;
                        }
                    }
                }
            }
        }
        if (col != 3){
            for (PublicPosition position : publicPositions) {
                // Obtains the position at the given index
                if (position.getColIndex() == col && position.getToken() != token) {
                        colFlag = false;
                    }
                }
            }
        } else {
            if (row < 3){
                for (PublicPosition position : publicPositions) {
                    // Obtains the position at the given index
                    if (position.getColIndex() == col && position.getToken() != token && position.getRowIndex < 3) {
                            colFlag = false;
                        }
                    }
                }
            } else {
                for (PublicPosition position : publicPositions) {
                    // Obtains the position at the given index
                    if (position.getColIndex() == col && position.getToken() != token && position.getRowIndex > 3) {
                            colFlag = false;
                        }
                    }
                }
            }
        }
        return rowFlag || colFlag;
    }

}
