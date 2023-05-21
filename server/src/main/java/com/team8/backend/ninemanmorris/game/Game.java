package com.team8.backend.ninemanmorris.game;

import com.team8.backend.ninemanmorris.enums.Phase;
import com.team8.backend.ninemanmorris.enums.Token;
import com.team8.backend.ninemanmorris.moves.*;
import com.team8.backend.ninemanmorris.positions.Position;
import com.team8.backend.ninemanmorris.positions.PublicPosition;

public class Game {
    private Board board;
    private Player playerOne;
    private Player playerTwo;

    private Player currPlayer;
    private Boolean gameStatus = false;

    /**
     * Constructor for game class
     */
    public Game() {
        int[][] initialBoard = {
                { 1, 0, 0, 1, 0, 0, 1 },
                { 0, 1, 0, 1, 0, 1, 0 },
                { 0, 0, 1, 1, 1, 0, 0 },
                { 1, 1, 1, 0, 1, 1, 1 },
                { 0, 0, 1, 1, 1, 0, 0 },
                { 0, 1, 0, 1, 0, 1, 0 },
                { 1, 0, 0, 1, 0, 0, 1 }
        };
        // Creating board based on initial board
        this.board = new Board(initialBoard);
        this.playerOne = new Player("Player 1", Token.PLAYER_1);
        this.playerTwo = new Player("Player 2", Token.PLAYER_2);
        this.currPlayer = playerOne;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getCurrPlayer() {
        return this.currPlayer;
    }

    public void setCurrPlayer(Player player) {
        this.currPlayer = player;
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    public void setGameStatus(boolean status) {
        this.gameStatus = status;
    }

    public boolean getGameStatus() {
        return this.gameStatus;
    }

    /**
     * For making a move base on whether a player has a certain phase
     * 
     * @param fromRow - fromRow Index depending on
     * @param fromCol
     * @param toRow
     * @param toCol
     * @return
     */
    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        boolean moveStatus;
        if (currPlayer.getMovementPhase() == Phase.PLACEMENT) {
            moveStatus = this.placeMove(fromRow, fromCol, toRow, toCol);
        } else if (currPlayer.getMovementPhase() == Phase.MOVEMENT) {
            // IF ther exist valid moves
            moveStatus = slideMove(fromRow, fromCol, toRow, toCol);
        } else {
            moveStatus = removeMove(fromRow, fromCol, toRow, toCol);
        }

        // if (currPlayer.getMovementPhase() == Phase.MOVEMENT) {
        // // If there are no valid moves left
        // if (!checkValidMovesLeft()) {
        // setGameEnd();
        // }
        // }

        // Returning the boolean status of the move
        return moveStatus;
    }

    /**
     * For starting placement phase,
     * if valid check if the phase
     * else if valid swap players
     * 
     * @return boolean value on whther it was a valid place move
     */
    public boolean placeMove(int fromRow, int fromCol, int toRow, int toCol) {
        Move newMove = new PlaceMove(fromRow, fromCol, toRow, toCol, this.currPlayer, this.board);
        Boolean isValid = newMove.getMoveStatus();

        // Swaps if the move was valid, and the plauer
        // does not need to remove; as removal phase
        // indicates that it is the current players
        // turn again and they need to remove a piece
        if (isValid & currPlayer.getMovementPhase() != Phase.REMOVE) {
            swapPlayers();
        }

        return isValid;
    }

    /**
     * For a regular slide move
     * 
     * @return
     */
    public boolean slideMove(int fromRow, int fromCol, int toRow, int toCol) {
        Move newMove = new SlideMove(fromRow, fromCol, toRow, toCol, this.currPlayer, this.board);
        Boolean isValid = newMove.getMoveStatus();

        // Check if valid and ready to swap players
        if (isValid & currPlayer.getMovementPhase() != Phase.REMOVE) {
            swapPlayers();

            // Check if opponent has run out of moves
            if (currPlayer.getNumTokens() > 3 && currPlayer.getMovementPhase() == Phase.MOVEMENT) {
                if (!checkValidMovesLeft()) {
                    // Game ends
                    setGameEnd();
                }
            }
        }

        return isValid;
    }

    /**
     * For removig a piece from the board,
     * and swapping players if successful
     * and valid piece to remove
     * 
     * @return boolean value on if the move was successful
     */
    public boolean removeMove(int fromRow, int fromCol, int toRow, int toCol) {
        Move newMove = new RemoveMove(fromRow, fromCol, toRow, toCol, this.currPlayer, this.board);
        Boolean isValid = newMove.getMoveStatus();

        if (isValid) {
            // Check if game is over
            swapPlayers();
            this.currPlayer.decrementTokens();

            // If the currPlayer has less than 3
            if (currPlayer.getNumTokens() < 3) {
                setGameEnd();
            }
        }

        return isValid;
    }

    /**
     * Checker for the game ending
     */
    private void setGameEnd() {
        // Swaps players
        this.swapPlayers();
        // Sets the game status
        this.setGameStatus(!this.getGameStatus());
    }

    private boolean checkValidMovesLeft() {

        boolean flag = false;

        // Loops through all the public positions
        for (PublicPosition position : this.board.getPublicPositions()) {
            // If the token of the curr player
            if (position.getToken() == currPlayer.getPlayerToken()) {
                // Check for its neighbours and look for an empty tile
                for (Position neighbour : position.getNeighbours()) {
                    if (neighbour.getToken() == Token.TILE) {
                        flag = true;
                    }
                }

            }
        }

        return flag;
    }

    /**
     * Helper function for Swaping the Players
     */
    private void swapPlayers() {
        if (this.currPlayer == playerOne) {
            this.currPlayer = playerTwo;
        } else {
            this.currPlayer = playerOne;
        }
    }

}
