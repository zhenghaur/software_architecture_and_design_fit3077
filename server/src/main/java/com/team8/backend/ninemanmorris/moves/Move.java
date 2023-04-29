package com.team8.backend.ninemanmorris.moves;

import com.team8.backend.ninemanmorris.Board;
import com.team8.backend.ninemanmorris.Player;

public abstract class Move {
    
public abstract String execute(Player player, Board board);

public Move nextMove() {
    return null;
};

}
