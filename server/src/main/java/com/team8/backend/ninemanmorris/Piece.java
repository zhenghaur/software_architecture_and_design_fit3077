package com.team8.backend.ninemanmorris;

public class Piece {
    private Board board;

    private Player player;

    public Piece(Player player, Board board){
        this.board = board;
        this.player = player;
    };
}
