package com.team8.backend.ninemanmorris.moves;

import java.util.ArrayList;

import com.team8.backend.ninemanmorris.enums.Phase;
import com.team8.backend.ninemanmorris.enums.Token;
import com.team8.backend.ninemanmorris.game.Board;
import com.team8.backend.ninemanmorris.game.Player;
import com.team8.backend.ninemanmorris.positions.PublicPosition;

public class RemoveMove extends Move {

    public RemoveMove(int fromRow, int fromCol, int toRow, int toCol, Player player, Board board) {
        super(fromRow, fromCol, toRow, toCol, player, board);
        // Calling the removeMove Function
        removeMove(fromRow, fromCol);
    }

    private void removeMove(int fromRow, int fromCol) {
        ArrayList<PublicPosition> publicPositions = this.board.getPublicPositions();

        for (PublicPosition position : publicPositions) {
            // Obtains the position at the given index
            if (position.getRowIndex() == fromRow & position.getColIndex() == fromCol) {
                // If the position is empty, it is not in a mill and it is not the players token
                if (!publicPositionEmpty(position) & position.getToken() != this.player.getPlayerToken()) {
                    // If it is not in mill
                    if (!checkMillPosition(position)) {
                        removeMoveHelper(position);
                    }
                    // Else if it is in a mill, Check for the case that every other token is in the
                    // mill
                    else if (millPositionHelper(position.getToken())) {
                        removeMoveHelper(position);
                    }
                }
            }
        }
    }

    /**
     * Helper function for the removeMove, initialising states
     * based on the outcome
     * 
     * @param position
     */
    private void removeMoveHelper(PublicPosition position) {
        position.removePlayer();
        this.moveStatus = true;
        if (this.player.getNumStorageTokens() > 0) {
            this.player.setMovementPhase(Phase.PLACEMENT);
        } else {
            this.player.setMovementPhase(Phase.MOVEMENT);
        }
    }

    /**
     * Method for checking the case whereby opponent has
     * all tokens inside a mill.
     * 
     * @return true false if all positions of a particular token is in a mill
     */
    private boolean millPositionHelper(Token token) {
        int tokenCount = 0;
        int inMillCount = 0;

        // Loops through all the public positions
        for (PublicPosition position : this.board.getPublicPositions()) {
            if (position.getToken() == token) {
                tokenCount++;
                if (checkMillPosition(position)) {
                    inMillCount++;
                }
            }
        }

        return tokenCount == inMillCount;
    }
}
