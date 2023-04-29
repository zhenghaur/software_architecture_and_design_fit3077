package com.team8.backend.ninemanmorris;

import java.util.ArrayList;

public class Game {
    private Board board;
    private Player playerOne;
    private Player playerTwo;

    private Player currPlayer;

    public Game() {
        this.board = new Board();
        this.playerOne = new Player("Player 1", 2);
        this.playerTwo = new Player("Player 2", 3);
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

    private boolean validateMove(Board newBoard) {
        boolean isValid = false;
        ArrayList<ArrayList<Integer>> currBoardState = this.board.getBoardState();
        ArrayList<ArrayList<Integer>> newBoardState = newBoard.getBoardState();
        for (int i = 0; i < currBoardState.size(); i++) {
            if (currBoardState != newBoardState) {
                // Check current row if there is difference
                for (int j = 0; j < currBoardState.get(i).size(); j++) {
                    if (currBoardState.get(i).get(j) != newBoardState.get(i).get(j)) {
                        // Validate the change if spot is not unavailable, and move is made by current
                        // player
                        if ((currBoardState.get(i).get(j) != 1)
                                && (newBoardState.get(i).get(j) == this.playerOne.getPlayerToken()
                                        || newBoardState.get(i).get(j) == this.playerTwo.getPlayerToken())) {
                            System.out.println("REACHED FALSE");
                            return false;
                        }
                        if (currBoardState.get(i).get(j) == 1
                                && newBoardState.get(i).get(j) == this.currPlayer.getPlayerToken()) {
                            System.out.print("RECAHCES");
                            isValid = true;
                        } else if (currBoardState.get(i).get(j) == this.currPlayer.getPlayerToken()
                                && newBoardState.get(i).get(j) == 1) {
                            System.out.println("RECAHES SECOND");
                            System.out.println(currBoardState.get(i).get(j));
                            System.out.println(newBoardState.get(i).get(j));
                            System.out.println(this.playerOne.getPlayerToken());
                            System.out.println(this.playerTwo.getPlayerToken());
                            isValid = true;
                        }
                    }
                }
            }
        }
        return isValid;
    }

    public boolean makeMove(Board newBoard) {
        Boolean isValid = false;
        isValid = this.validateMove(newBoard);

        if (isValid) {
            setBoard(newBoard);
            // Swap player turns
            if (this.getCurrPlayer() == this.playerOne) {
                this.setCurrPlayer(this.playerTwo);
            } else if (this.getCurrPlayer() == this.playerTwo) {
                this.setCurrPlayer(this.playerOne);
            }
        }

        return isValid;
    }

}
