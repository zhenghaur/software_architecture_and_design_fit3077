package com.team8.backend.ninemanmorris.moves;

public class BasicMove extends Move {
    
    private Piece target;

    private Position destination;

    public BasicMove(Piece target, Position destination){
        this.target = target;
        this.destination = destination;
    };

    @Override
    public String execute(Player player, Board board){};

    @Override
    public Move nextMove(){};

}
