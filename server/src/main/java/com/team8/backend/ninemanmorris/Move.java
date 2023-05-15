package com.team8.backend.ninemanmorris;

import java.util.ArrayList;

/**
 * Class for modularising the Move function
 */
public class Move {
    // Failed by default
    private boolean moveStatus = false;
    // Variables for using
    private Board board;
    private Player player;

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

        if (player.getMovementPhase() == Phase.PLACEMENT) {
            placeMove(toRow, toCol);
        } else if (player.getMovementPhase() == Phase.MOVEMENT) {
            slideMove(fromRow, fromCol, toRow, toCol);
        } else if (player.getMovementPhase() == Phase.REMOVE) {
            removeMove(fromRow, fromCol);
        }
    }

    /**
     * Placing tokens at locatio
     * 
     * @param toRow - Rox coord of the position to place
     * @param toCol - Col coord of the position to place
     */
    private void placeMove(int toRow, int toCol) {
        ArrayList<PublicPosition> publicPositions = this.board.getPublicPositions();

        for (PublicPosition position : publicPositions) {
            // Obtains the position at the given index
            if (position.getRowIndex() == toRow & position.getColIndex() == toCol) {
                // If the position is empty
                if (this.publicPositionEmpty(position)) {
                    // Checks for valid playerToken to be set
                    if (this.player.getPlayerToken() == Token.PLAYER_1) {
                        position.setPlayerOne();
                    } else {
                        position.setPlayerTwo();
                    }
                    this.player.decrementStorageTokens();
                    this.moveStatus = true;
                }
            }
        }
    }

    private void slideMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Add validation for move
        // Check if the from and to is the same = false
        // Check if the players turn and is not their token = false
        // Check if the piece is not TILE TOKEN = false
        // Check if the to in not in the from's neighbour = false
        // isValid based on this

        // ceckong neighbours
    }

    private void removeMove(int fromRow, int fromCol) {

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

    /**
     * returns if the publick Position is empty
     * 
     * @param position
     * @return
     */
    private boolean publicPositionEmpty(PublicPosition position) {
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
