package com.team8.backend.ninemanmorris.moves;

import com.team8.backend.ninemanmorris.Board;
import com.team8.backend.ninemanmorris.Player;
import com.team8.backend.ninemanmorris.PublicPosition;
import com.team8.backend.ninemanmorris.Token;

public class SlideMove extends Move {

    public SlideMove(int fromRow, int fromCol, int toRow, int toCol, Player player, Board board) {
        super(fromRow, fromCol, toRow, toCol, player, board);
        // Calling the slide move function
        slideMove(fromRow, fromCol, toRow, toCol);
    }

    private boolean slideMove(int fromRow, int fromCol, int toRow, int toCol) {

        boolean isValid = true;

        // Verifying that positions exist
        if (!publicPositionExists(fromRow, fromCol) || !publicPositionExists(toRow, toCol)) {
            return false;
        }

        // Initializing fromPosition and toPosition
        PublicPosition fromPosition = null;
        PublicPosition toPosition = null;

        // Finding fromPosition and toPosition
        for (PublicPosition position : this.board.getPublicPositions()) {
            if (position.getRowIndex() == fromRow && position.getColIndex() == fromCol) {
                fromPosition = position;
            } else if (position.getRowIndex() == toRow && position.getColIndex() == toCol) {
                toPosition = position;
            }
        }

        // Checking if the two positions are the same
        if (fromPosition == toPosition) {
            return false;
        }

        // Checking if token being moved belongs to currPlayer
        if (fromPosition.getToken() != this.player.getPlayerToken()) {
            return false;
        }

        // Check if the piece is a tile token
        if (toPosition.getToken() != Token.TILE) {
            return false;
        }

        // Loop through check if to is from's neighbour

        return isValid;

        // Add validation for move
        // Check if the from and to is the same = false
        // Check if the players turn and is not their token = false
        // Check if the piece is not TILE TOKEN = false
        // Check if the to in not in the from's neighbour = false
        // isValid based on this

        // ceckong neighbours
    }

    /**
     * Determines if a public position exists in publicPositions array.
     * 
     * @param rowIndex - row of the checked position
     * @param colIndex - col of the checked position
     * 
     * @return - true if it exists, false otherwise
     */
    private boolean publicPositionExists(int rowIndex, int colIndex) {
        boolean positionExists = false;
        for (PublicPosition position : this.board.getPublicPositions()) {
            if (position.getRowIndex() == rowIndex && position.getColIndex() == colIndex) {
                positionExists = true;
            }
        }
        return positionExists;
    }
}
