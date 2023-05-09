package com.team8.backend.ninemanmorris;

import java.util.ArrayList;
import java.util.List;

import com.team8.backend.ninemanmorris.moves.Move;

public class Player {
    private List<Piece> pieces = new ArrayList<>();

    private String playerName;
    private Token playerToken;

    public Player(String playerName, Token playerToken) {
        this.playerName = playerName;
        this.playerToken = playerToken;
    }

    public Token getPlayerToken() {
        return this.playerToken;
    }

    public void addPiece() {

    }

    public Move playTurn() {
        return null;

    }
}
