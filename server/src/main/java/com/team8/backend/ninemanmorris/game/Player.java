package com.team8.backend.ninemanmorris.game;

import com.team8.backend.ninemanmorris.enums.Phase;
import com.team8.backend.ninemanmorris.enums.Token;

/**
 * Player Class
 */
public class Player {
    public static int DEFAULT_NUM_STORAGE_TOKENS = 9;
    public static int DEFAULT_NUM_TOKENS = 9;

    private String playerName;
    private Token playerToken;

    // Player Phase to be checked for validation of move
    private Phase movementPhase = Phase.PLACEMENT;

    private int numStorageTokens = Player.DEFAULT_NUM_STORAGE_TOKENS;
    private int numTokens = Player.DEFAULT_NUM_TOKENS;

    /**
     * Player Constructor
     * 
     * @param playerName  - Stirng name of the player
     * @param playerToken - Token of the player
     */
    public Player(String playerName, Token playerToken) {
        this.playerName = playerName;
        this.playerToken = playerToken;
    }

    /**
     * Getter for playerName
     * 
     * @return String playerName
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Getter for the Plauer Token
     * 
     * @return Token representing Player
     */
    public Token getPlayerToken() {
        return this.playerToken;
    }

    /**
     * Setter for the Movement Phase of the Player
     * 
     * @param movementPhase
     */
    public void setMovementPhase(Phase movementPhase) {
        this.movementPhase = movementPhase;
    }

    /**
     * Getter forthe Movement Phase of the Plauer
     * 
     * @return Phase movement phase of the player
     */
    public Phase getMovementPhase() {
        return this.movementPhase;
    }

    /**
     * Decrements the players number of tokens
     */
    public void decrementTokens() {
        this.numTokens -= 1;
    }

    /**
     * Getter for the number of tokens a Player has
     * 
     * @return int number of tokens
     */
    public int getNumTokens() {
        return this.numTokens;
    }

    /**
     * Setter for the number of tokens a Player has
     * 
     * @return int number of tokens
     */
    public void setNumTokens(int newNumTokens) {
        this.numTokens = newNumTokens;
    }

    /**
     * Decrementer for the number of storage tokens.
     */
    public void decrementStorageTokens() {
        this.numStorageTokens -= 1;

        // Checks if the number of tokens in storage is 0 indicating end
        if (numStorageTokens <= 0) {
            this.movementPhase = Phase.MOVEMENT;
        }
    }

    /**
     * Increments storage tokens
     */
    public void incrementStorageTokens() {
        this.numStorageTokens += 1;
    }

    /**
     * Increments player's number of tokens
     */
    public void incrementTokens() {
        this.numTokens += 1;
    }

    /**
     * Getter for Number of Storage Tokens
     * 
     * @return number of storage tokens
     */
    public int getNumStorageTokens() {
        return this.numStorageTokens;
    }

    /**
     * Setter for Number of Storage Tokens
     * 
     * @return number of storage tokens
     */
    public void setNumStorageTokens(int newNumStorageTokens) {
        this.numStorageTokens = newNumStorageTokens;
    }
}
