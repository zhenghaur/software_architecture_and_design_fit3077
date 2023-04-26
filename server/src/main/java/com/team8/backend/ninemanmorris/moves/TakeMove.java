package com.team8.backend.ninemanmorris.moves;

public class TakeMove extends Move {
    
    private Piece target;

    public TakeMove(Piece target){
        this.target = target;
    };

    @Override
    public String execute(Player player, Board board){};

    @Override
    public Move nextMove(){};
}
