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

    public void addNeighbors(Position[] newNeighbors){
        for (Position neighbor: newNeighbors){
            this.neighbors.add(neighbor)
        }
    };

    public int getRow(){
        return this.row;
    };

    public int getCol(){
        return this.col;
    };
}
