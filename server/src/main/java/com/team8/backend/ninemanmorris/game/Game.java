package com.team8.backend.ninemanmorris.game;

import java.util.ArrayList;
import java.util.Stack;

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
    private Stack<int[]> moveStack;

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
        this.moveStack = new Stack<int[]>();
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

        // { Player, Phase, fromRow, fromCol, toRow, toCol }
        int[] moveData = { this.currPlayer.getPlayerToken().getToken(), this.currPlayer.getMovementPhase().getPhase(),
                fromRow, fromCol, toRow, toCol };
        this.moveStack.push(moveData);

        if (currPlayer.getMovementPhase() == Phase.PLACEMENT) {
            moveStatus = this.placeMove(fromRow, fromCol, toRow, toCol);
        } else if (currPlayer.getMovementPhase() == Phase.MOVEMENT) {
            // IF ther exist valid moves
            moveStatus = slideMove(fromRow, fromCol, toRow, toCol);
        } else {
            moveStatus = removeMove(fromRow, fromCol, toRow, toCol);
        }

        // Check for invalid move
        if (!moveStatus) {
            this.moveStack.pop();
        }

        return moveStatus;
    }

    /**
     * Sets game state to uploaded file.
     * 
     * @param playerOneTokensLeft - number of tokens player 1 has
     * @param playerTwoTokensLeft - number of tokens player 2 has
     * @param playerOneTokensStorage - number of tokens player 1 has in storage
     * @param playerTwoTokensStorage - number of tokens player 2 has in storage
     * @param gameOver - boolean representing if game is over
     * @param playerTurn - current player
     * @param playerPhase - movement phase
     * @param boardState - state of the board
     * @param myStack - movement stack
     * 
     * @return true if valid, false otherwise
     */
    public boolean setState(int playerOneTokensLeft, int playerTwoTokensLeft, int playerOneTokensStorage,
            int playerTwoTokensStorage, boolean gameOver, int playerTurn, int playerPhase, int[][] boardState,
            Stack myStack) {
        try {
            // set both players number of tokens left
            this.playerOne.setNumTokens(playerOneTokensLeft);
            this.playerTwo.setNumTokens(playerTwoTokensLeft);

            // set both players number of tokens in storage
            this.playerOne.setNumStorageTokens(playerOneTokensStorage);
            this.playerTwo.setNumStorageTokens(playerTwoTokensStorage);

            // set game state
            this.gameStatus = gameOver;

            // set player turn
            this.currPlayer = (playerTurn == 1) ? this.playerOne : this.playerTwo;

            // set player phase
            if (playerPhase == Phase.PLACEMENT.getPhase()) {
                this.currPlayer.setMovementPhase(Phase.PLACEMENT);
            } else if (playerPhase == Phase.MOVEMENT.getPhase()) {
                this.currPlayer.setMovementPhase(Phase.MOVEMENT);
            } else {
                this.currPlayer.setMovementPhase(Phase.REMOVE);
            }

            // set board state
            this.board = new Board(boardState);

            // set stack
            this.moveStack = myStack;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Getter for the moveStack
     * 
     * @return
     */
    public Stack getMoveStack() {
        return this.moveStack;
    }

    /**
     * This function handles undo move using the movement stack to reverse to the previous board state/undo latest move
     * 
     * @return true for validity
     */
    public boolean undoMove() {
        boolean valid = false;

        // Checks that the move stack is not empty
        if (!this.moveStack.isEmpty()) {
            // Obtaining the moveData
            int[] moveData = moveStack.pop();
            int playerToken = moveData[0];
            int movementPhase = moveData[1];
            int fromRow = moveData[2];
            int fromCol = moveData[3];
            int toRow = moveData[4];
            int toCol = moveData[5];

            ArrayList<PublicPosition> publicPositions = this.board.getPublicPositions();

            // if move was a place we remove the piece currently at the position toRow,toCol
            // add token to storage
            if (movementPhase == Phase.PLACEMENT.getPhase()) {
                for (PublicPosition position : publicPositions) {
                    // gets the position to clear
                    if (position.getRowIndex() == toRow & position.getColIndex() == toCol) {
                        // Temporary then at the position
                        Token tempToken = position.getToken();
                        // Removes the token at the player
                        position.removePlayer();

                        // Sets the tempPlayer
                        Player tempPlayer;
                        // Obtains which player
                        if (this.playerOne.getPlayerToken() == tempToken) {
                            tempPlayer = this.playerOne;
                        } else {
                            tempPlayer = this.playerTwo;
                        }

                        // increments the players storage tokens
                        tempPlayer.incrementStorageTokens();
                    }
                }
            }
            // if move was a move we move the toRow, toCol to fromRow, fromCol
            else if (movementPhase == Phase.MOVEMENT.getPhase()) {
                for (PublicPosition position : publicPositions) {
                    // gets the position to move to
                    if (position.getRowIndex() == fromRow & position.getColIndex() == fromCol) {
                        // Add position
                        if (playerToken == Token.PLAYER_1.getToken()) {
                            position.setPlayerOne();
                        } else {
                            position.setPlayerTwo();
                        }
                    } else if (position.getRowIndex() == toRow & position.getColIndex() == toCol) {
                        // Remove token
                        position.removePlayer();
                    }
                }
            }
            // if move was a remove, we place the opponents piece at the fromRow, fromCol
            // Add to the opponents num tokens
            else if (movementPhase == Phase.REMOVE.getPhase()) {
                for (PublicPosition position : publicPositions) {
                    // gets the position to place
                    if (position.getRowIndex() == fromRow & position.getColIndex() == fromCol) {
                        // Store player
                        Player tempPlayer;
                        // Adds token
                        if (playerToken == Token.PLAYER_1.getToken()) {
                            position.setPlayerTwo();
                            tempPlayer = playerTwo;
                        } else {
                            position.setPlayerOne();
                            tempPlayer = playerOne;
                        }

                        // Updates tokens left data
                        tempPlayer.incrementTokens();
                    }
                }
            }

            // Setting valid
            valid = true;
            // Set valid currPlayer
            if (playerToken == playerOne.getPlayerToken().getToken()) {
                this.currPlayer = playerOne;
            } else {
                this.currPlayer = playerTwo;
            }
            // Set valid movePhase
            if (movementPhase == Phase.PLACEMENT.getPhase()) {
                this.currPlayer.setMovementPhase(Phase.PLACEMENT);
            } else if (movementPhase == Phase.MOVEMENT.getPhase()) {
                this.currPlayer.setMovementPhase(Phase.MOVEMENT);
            } else {
                this.currPlayer.setMovementPhase(Phase.REMOVE);
            }
            this.gameStatus = false;
        }

        return valid;
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
