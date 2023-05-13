package com.team8.backend.ninemanmorris;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        int gameId = gameController.createGame();
        gameController.getBoard(gameId).printBoard();

        gameController.getBoard(gameId).getBoardStateInt();
        System.out.println(" ");

        gameController.getBoard(gameId).printBoard();

        System.out.println(gameController.getGame(gameId).getCurrPlayer().getPlayerToken().getToken());
        System.out.println(gameController.getGame(gameId).makeMove(1, 3, 0, 0));

        System.out.println(" ");

        gameController.getBoard(gameId).printBoard();

        System.out.println(gameController.getGame(gameId).getCurrPlayer().getPlayerToken().getToken());

        // AGAIn
        System.out.println(gameController.getGame(gameId).makeMove(0, 0, 1, 3));

        gameController.getBoard(gameId).printBoard();

    }
}
