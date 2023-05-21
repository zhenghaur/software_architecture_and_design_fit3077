package com.team8.backend.ninemanmorris;

import com.team8.backend.ninemanmorris.enums.Token;
import com.team8.backend.ninemanmorris.moves.Move;
import com.team8.backend.ninemanmorris.positions.Position;
import com.team8.backend.ninemanmorris.positions.PublicPosition;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        int gameId = gameController.createGame();
        gameController.getBoard(gameId).printBoard();

        int tokenCount = 0;
        int inMillCount = 0;
        Token token = Token.PLAYER_2;

        for (PublicPosition position : gameController.getBoard(gameId).getPublicPositions()) {
            // if (position.getToken() == token) {
            // tokenCount++;
            // }
            // System.out.println(position.getNeighbours().size());
        }

        boolean flag = false;
        for (PublicPosition position : gameController.getBoard(gameId).getPublicPositions()) {
            if (position.getToken() == token) {
                for (Position neighbour : position.getNeighbours()) {
                    if (neighbour.getToken() == Token.TILE) {
                        flag = true;
                    }
                }
            }
        }
        System.out.println(flag);

        // gameController.getBoard(gameId).getBoardStateInt();
        // System.out.println(" ");

        // gameController.getBoard(gameId).printBoard();

        // System.out.println(gameController.getGame(gameId).getCurrPlayer().getPlayerToken().getToken());
        // System.out.println(gameController.getGame(gameId).makeMove(1, 3, 0, 0));

        // System.out.println(" ");

        // gameController.getBoard(gameId).printBoard();

        // System.out.println(gameController.getGame(gameId).getCurrPlayer().getPlayerToken().getToken());

        // // AGAIn
        // System.out.println(gameController.getGame(gameId).makeMove(0, 0, 1, 3));

        // gameController.getBoard(gameId).printBoard();
        // gameController.getGame(gameId).makeMove(0, 0, 0, 3);
        // System.out.println("");
        // gameController.getBoard(gameId).printBoard();
        // gameController.getGame(gameId).makeMove(0, 0, 3, 0);
        // gameController.getGame(gameId).makeMove(0, 0, 0, 6);
        // System.out.println("");
        // gameController.getBoard(gameId).printBoard();
        // System.out.println(gameController.getGame(gameId).makeMove(0, 0, 3, 0));
        // System.out.println(gameController.getGame(gameId).makeMove(3, 0, 3, 0));
        // gameController.getBoard(gameId).printBoard();

        // // Chceking for mill at alocaiton
        // for (PublicPosition position :
        // gameController.getGame(gameId).getBoard().getPublicPositions()) {
        // if (position.getRowIndex() == 1 & position.getColIndex() == 3) {
        // Move temp = new Move(0, 0, 0, 0, null,
        // gameController.getGame(gameId).getBoard());
        // System.out.println(temp.checkMillPosition(position));
        // }
        // }

    }
}
