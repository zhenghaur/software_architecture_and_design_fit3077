package com.team8.backend.ninemanmorris.moves;

import com.team8.backend.ninemanmorris.Board;
import com.team8.backend.ninemanmorris.Piece;
import com.team8.backend.ninemanmorris.Player;
import com.team8.backend.ninemanmorris.Position;

public class JumpMove extends Move {
    
    private Piece target;

    private Position destination;

    public JumpMove(Piece target, Position destination){
        this.target = target;
        this.destination = destination;
    };

    @Override
    public String execute(Player player, Board Board) {
        return null;
    };

    @Override
    public Move nextMove() {
        return null;
    };

}
