package com.team8.backend.ninemanmorris;

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