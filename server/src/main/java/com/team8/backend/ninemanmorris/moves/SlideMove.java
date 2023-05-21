package com.team8.backend.ninemanmorris.moves;

import com.team8.backend.ninemanmorris.enums.Phase;
import com.team8.backend.ninemanmorris.enums.Token;
import com.team8.backend.ninemanmorris.game.Board;
import com.team8.backend.ninemanmorris.game.Player;
import com.team8.backend.ninemanmorris.positions.Position;
import com.team8.backend.ninemanmorris.positions.PublicPosition;

public class SlideMove extends Move {

    /**
     * Constructor for the slide move lcass
     * 
     * @param fromRow
     * @param fromCol
     * @param toRow
     * @param toCol
     * @param player
     * @param board
     */
    public SlideMove(int fromRow, int fromCol, int toRow, int toCol, Player player, Board board) {
        super(fromRow, fromCol, toRow, toCol, player, board);
        // Calling the slide move function
        slideMove(fromRow, fromCol, toRow, toCol);
    }

    private void slideMove(int fromRow, int fromCol, int toRow, int toCol) {

        boolean isValid = true;

        // Verifying that positions exist
        if (!publicPositionExists(fromRow, fromCol) || !publicPositionExists(toRow, toCol)) {
            isValid = false;
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
            isValid = false;
        }

        // Checking if token being moved belongs to currPlayer
        else if (fromPosition.getToken() != this.player.getPlayerToken()) {
            isValid = false;
        }

        // Check if the piece is a tile token
        else if (toPosition.getToken() != Token.TILE) {
            isValid = false;
        }

        // If the player has more than 3 tokens, check if move is to a neighbouring
        // position
        else if (this.player.getNumTokens() > 3) {

            boolean isNeighbour = false;
            // Loop through check if to is from's neighbour
            for (Position neighbour : fromPosition.getNeighbours()) {
                if (neighbour == toPosition) {
                    isNeighbour = true;
                    break;
                }
            }

            // Set false if toPosition is not a neighbour of fromPosition
            if (!isNeighbour) {
                isValid = false;
            }
        }

        // Check if valid to call slideMoveHelper
        if (isValid) {
            fromPosition.removePlayer();
            if (player.getPlayerToken() == Token.PLAYER_1) {
                toPosition.setPlayerOne();
            } else {
                toPosition.setPlayerTwo();
            }

            // Check if the added token makes a mill
            slideMoveHelper(toPosition);
        }

        // Add validation for move
        // Check if the from and to is the same = false
        // Check if the players turn and is not their token = false
        // Check if the piece is not TILE TOKEN = false
        // Check if the to in not in the from's neighbour = false
        // isValid based on this

        // ceckong neighbours
    }

    /***
     * Slide Move Helper checking for mill and removing
     * 
     * @param position
     */
    private void slideMoveHelper(PublicPosition position) {
        this.moveStatus = true;
        if (this.checkMillPosition(position)) {
            this.player.setMovementPhase(Phase.REMOVE);
        }
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
