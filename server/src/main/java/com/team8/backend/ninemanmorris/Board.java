package com.team8.backend.ninemanmorris;

public class Board {

    private List<Position> positions = new ArrayList<>();

    private List<Piece> pieces = new ArrayList<>();

    private Map<Piece, Location> positionOfPieces = new Hashmap<Piece, Location>();
    
    private Map<Location, Piece> pieceOfPositions = new Hashmap<Location, Piece>();

    public Board(){};

    public void addPiece(Piece piece, Position position){};

    public void removePiece(Position position){};

    public void movePiece(Position oldPosition, Position newPosition){};

    public bool isPositionEmpty(Position position){};

    public Piece getPiece(Position position){};

}
