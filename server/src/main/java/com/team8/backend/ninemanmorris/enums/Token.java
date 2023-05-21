package com.team8.backend.ninemanmorris.enums;

/***
 * Enum class for representing the board
 * objects and their view state.
 */
public enum Token {

    EMPTY(0),
    TILE(1),
    PLAYER_1(2),
    PLAYER_2(3);

    private final int token;

    Token(int token) {
        this.token = token;
    }

    public int getToken() {
        return token;
    }
}