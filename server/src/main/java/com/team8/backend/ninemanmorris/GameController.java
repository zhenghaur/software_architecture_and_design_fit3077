package com.team8.backend.ninemanmorris;

import java.util.HashMap;

import com.team8.backend.ninemanmorris.game.Board;
import com.team8.backend.ninemanmorris.game.Game;

public class GameController {
    /**
     * GameController for
     */
    private HashMap<Integer, Game> games;
    private int nextGameId;

    public GameController() {
        games = new HashMap<>();
        nextGameId = 1;
    }

    public int createGame() {
        int gameId = nextGameId;
        games.put(gameId, new Game());
        nextGameId++;
        return gameId;
    }

    /***
     * Getter for game using gameId to access the Hash Map
     * 
     * @param gameId - id of the game
     * @return returns the game with the input id
     */
    public Game getGame(int gameId) {
        return games.get(gameId);
    }

    /***
     * Getter for Board of the gameId
     * 
     * @param gameId - id of te game
     * @return returns te game's board with the input id
     */
    public Board getBoard(int gameId) {
        Game game = getGame(gameId);
        return game.getBoard();
    }
}
