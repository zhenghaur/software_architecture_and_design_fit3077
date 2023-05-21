package com.team8.backend.ninemanmorris.positions;

import com.team8.backend.ninemanmorris.enums.Token;

/***
 * Position class that does not allow for pieces to be placed upon.
 */
public class PrivatePosition implements Position {
    // Static default Token
    final static Token PRIVATE_POSITION_TOKEN = Token.EMPTY;
    final static Boolean PRIVATE_POSITION_ENTRANCE = false;
    private int rowIndex;
    private int colIndex;

    public PrivatePosition(int row, int col) {
        this.rowIndex = row;
        this.colIndex = col;
    }

    @Override
    public boolean canEnter() {
        return PrivatePosition.PRIVATE_POSITION_ENTRANCE;
    }

    /* Getters & Setters */
    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColIndex() {
        return this.colIndex;
    }

    @Override
    public Token getToken() {
        return PrivatePosition.PRIVATE_POSITION_TOKEN;
    }
}