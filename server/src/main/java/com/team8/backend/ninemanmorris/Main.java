package com.team8.backend.ninemanmorris;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        int gameId = gameController.createGame();
        gameController.getBoard(gameId).printBoard();
        System.out.println(gameController.getBoard(gameId).getBoardStateInt());

        // Game game = new Game();
        // game.getBoard().printBoard();
        // game.getBoard().makeMove(0, 3, 0, 0);
        // System.out.println(" ");
        // game.getBoard().printBoard();
    }
}
