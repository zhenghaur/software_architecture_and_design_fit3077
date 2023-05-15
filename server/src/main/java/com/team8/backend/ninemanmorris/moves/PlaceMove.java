package com.team8.backend.ninemanmorris.moves;

import java.util.ArrayList;

import com.team8.backend.ninemanmorris.Board;
import com.team8.backend.ninemanmorris.Player;
import com.team8.backend.ninemanmorris.PublicPosition;
import com.team8.backend.ninemanmorris.Token;

public class PlaceMove extends Move {

    public PlaceMove(int fromRow, int fromCol, int toRow, int toCol, Player player, Board board) {
        super(fromRow, fromCol, toRow, toCol, player, board);
        // Calling the placeMove Function
        placeMove(toRow, toCol);
    }

    /**
     * Placing tokens at location
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
                if (publicPositionEmpty(position)) {
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

}
