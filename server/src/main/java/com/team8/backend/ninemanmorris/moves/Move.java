package com.team8.backend.ninemanmorris.moves;

public abstract class Move {
    
public abstract String execute(Player player, Board board){};

public Move nextMove(){};

}
