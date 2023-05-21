package com.team8.backend.ninemanmorris;

import com.team8.backend.ninemanmorris.enums.Token;

public interface Position {
    boolean canEnter();

    Token getToken();

    int getRowIndex();

    int getColIndex();
}