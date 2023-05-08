package com.team8.backend.ninemanmorris;

/***
 * Position class that does not allow for pieces to be placed upon.
 */
public class PrivatePosition implements Position {
    // Static default Token
    final static Token PRIVATE_POSITION_TOKEN = Token.EMPTY;
    final static Boolean PRIVATE_POSITION_ENTRANCE = false;

    @Override
    public boolean canEnter() {
        return PrivatePosition.PRIVATE_POSITION_ENTRANCE;
    }

    @Override
    public Token getToken() {
        return PrivatePosition.PRIVATE_POSITION_TOKEN;
    }
}