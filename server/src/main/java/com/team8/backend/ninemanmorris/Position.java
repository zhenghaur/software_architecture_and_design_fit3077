package com.team8.backend.ninemanmorris;

public interface Position {
    boolean canEnter();

    Token getToken();

    int getRowIndex();

    int getColIndex();
}