package com.team8.backend.ninemanmorris;

public class Position {
    
    private List<Position> neighbors = new ArrayList<>();

    private Board board;

    private int row;

    private int col;

    public Position(int row, int col, Board board){
        this.row = row;
        this.col = col;
        this.board = board;
    };

    public void addNeighbors(List<Position> neighbors){};

    public int getRow(){};

    public int getCol(){};
}
