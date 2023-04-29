package com.team8.backend.ninemanmorris;

public class Board {

    private int boardId;

    private Position[] positions = new Position[24];

    private Position[][] rows = new Position[8];

    private Position[][] cols = new Position[8];

    private List<Piece> pieces = new ArrayList<>();

    private Map<Piece, Location> positionOfPieces = new Hashmap<Piece, Location>();

    private Map<Location, Piece> pieceOfPositions = new Hashmap<Location, Piece>();

    public Board(int boardId) {
        this.boardId = boardId;
        this.initPosition();
    };

    private void initPosition() {
        position[0] = new Position(0, 0, this);
        position[1] = new Position(0, 3, this);
        position[2] = new Position(0, 6, this);
        position[3] = new Position(1, 1, this);
        position[4] = new Position(1, 3, this);
        position[5] = new Position(1, 5, this);
        position[6] = new Position(2, 1, this);
        position[7] = new Position(2, 2, this);
        position[8] = new Position(2, 3, this);
        position[9] = new Position(3, 0, this);
        position[10] = new Position(3, 1, this);
        position[11] = new Position(3, 2, this);
        position[12] = new Position(3, 4, this);
        position[13] = new Position(3, 5, this);
        position[14] = new Position(3, 6, this);
        position[15] = new Position(4, 1, this);
        position[16] = new Position(4, 2, this);
        position[17] = new Position(4, 3, this);
        position[18] = new Position(5, 1, this);
        position[19] = new Position(5, 3, this);
        position[20] = new Position(5, 5, this);
        position[21] = new Position(6, 0, this);
        position[22] = new Position(6, 3, this);
        position[23] = new Position(6, 6, this);

        rows[0]={position[0],position[1],position[2]};
        rows[1]={position[3],position[4],position[5]};
        rows[2]={position[6],position[7],position[8]};
        rows[3]={position[9],position[10],position[11]};
        rows[4]={position[12],position[13],position[14]};
        rows[5]={position[15],position[16],position[17]};
        rows[6]={position[18],position[19],position[20]};
        rows[7]={position[21],position[22],position[23]};

        cols[0]={position[0],position[9],position[21]};
        cols[1]={position[3],position[16],position[18]};
        cols[2]={position[6],position[11],position[15]};
        cols[3]={position[1],position[4],position[7]};
        cols[4]={position[16],position[19],position[22]};
        cols[5]={position[8],position[12],position[17]};
        cols[6]={position[5],position[13],position[20]};
        cols[7]={position[2],position[14],position[23]};

    };

    public void addPiece(Piece piece, Position position) {
    };

    public void removePiece(Position position) {
    };

    public void movePiece(Position oldPosition, Position newPosition) {
    };

    public bool isPositionEmpty(Position position) {
    };

    public Piece getPiece(Position position) {
    };

}
