package com.team8.backend.ninemanmorris.positions;

import java.util.ArrayList;
import java.util.List;

import com.team8.backend.ninemanmorris.enums.Token;

/***
 * Position class that allows for pieces to be placed upon.
 */
public class PublicPosition implements Position {

    // Static default Token
    final static Token PUBLIC_POSITION_TOKEN = Token.TILE;
    final static Boolean PUBLIC_POSITION_ENTRANCE = true;

    private List<Position> neighbors = new ArrayList<>();
    private Token token = Token.TILE;
    private int rowIndex;
    private int colIndex;

    private boolean isFull;

    /***
     * Constructor for Public Position
     */
    public PublicPosition(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    @Override
    public boolean canEnter() {
        return PublicPosition.PUBLIC_POSITION_ENTRANCE && !this.isFull;
    }

    /* Getters & Setters */
    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColIndex() {
        return this.colIndex;
    }

    public List<Position> getNeighbours() {
        return this.neighbors;
    }

    /* TOKEN RELATED METHODS */

    @Override
    public Token getToken() {
        return this.token;
    }

    public void setPlayerOne() {
        this.token = Token.PLAYER_1;
        this.isFull = true;
    }

    public void setPlayerTwo() {
        this.token = Token.PLAYER_2;
        this.isFull = true;
    }

    public void removePlayer() {
        this.token = PublicPosition.PUBLIC_POSITION_TOKEN;
        this.isFull = false;
    }

    /**
     * Adds a Neighbour to the neighbour list
     * 
     * @param position - Position that is a valid neighbour
     */
    public void addNeighbour(Position position) {
        this.neighbors.add(position);
    }
}