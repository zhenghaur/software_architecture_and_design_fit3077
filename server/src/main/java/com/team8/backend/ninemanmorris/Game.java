package com.team8.backend.ninemanmorris;

import java.util.ArrayList;

public class Game {
    private Board board;
    private Player playerOne;
    private Player playerTwo;

    private Player currPlayer;

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
        if (currPlayer.getMovementPhase() == Phase.PLACEMENT) {
            return placeMove(fromRow, fromCol, toRow, toCol);
        } else if (currPlayer.getMovementPhase() == Phase.MOVEMENT) {
            return slideMove(fromRow, fromCol, toRow, toCol);
        } else {
            return removeMove(fromRow, fromCol, toRow, toCol);
        }
    }

    /**
     * For starting placement phase,
     * if valid check if the phase
     * else if valid swap players
     * 
     * @return boolean value on whther it was a valid place move
     */
    public boolean placeMove(int fromRow, int fromCol, int toRow, int toCol) {
        Move newMove = new Move(fromRow, fromCol, toRow, toCol, this.currPlayer, this.board);
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
        Boolean isValid = true;

        if (isValid) {
            this.board.makeMoveSlide(fromRow, fromCol, toRow, toCol);

            /* TASK: TODO: */
            // Can now add validation for after move - whther the player has a match 3
            // Maybe swap players maybe not depending
            // Maybe changing phase

            // Check if the other player has any moves left
            this.swapPlayers();
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
        Move newMove = new Move(fromRow, fromCol, toRow, toCol, this.currPlayer, this.board);
        Boolean isValid = newMove.getMoveStatus();

        if (isValid) {
            // Check if game is over
            swapPlayers();
        }

        return isValid;
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
