package com.team8.backend.ninemanmorris.moves;

public class JumpMove extends Move {
    
    private Piece target;

    private Position destination;

    public JumpMove(Piece target, Position destination){
        this.target = target;
        this.destination = destination;
    };

    @Override
    public String execute(Player player, Position Board){};

    @Override
    public Move nextMove(){};

}
