package com.team8.backend.ninemanmorris.moves;

import com.team8.backend.ninemanmorris.Board;
import com.team8.backend.ninemanmorris.Piece;
import com.team8.backend.ninemanmorris.Player;

public class TakeMove extends Move {
    
    private Piece target;

    public TakeMove(Piece target){
        this.target = target;
    };

    @Override
    public String execute(Player player, Board board) {
        return null;
    };

    @Override
    public Move nextMove() {
        return null;
    };
}
