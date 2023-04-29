package com.team8.backend.ninemanmorris;

public class Player {
    private List<Piece> pieces = new ArrayList<>();

    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    };

    public void addPiece();

    public Move playTurn() {
    };
}
