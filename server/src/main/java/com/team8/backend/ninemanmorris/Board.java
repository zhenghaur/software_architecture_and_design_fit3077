package com.team8.backend.ninemanmorris;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private ArrayList<ArrayList<Integer>> boardState;

    // private Position[] positions = new Position[24];

    // private Position[][] rows = new Position[8];
    
    // private Position[][] cols = new Position[8];

    // private List<Piece> pieces = new ArrayList<>();

    // private Map<Piece, Position> positionOfPieces = new Hashmap<Piece, Position>();
    
    // private Map<Position, Piece> pieceOfPositions = new Hashmap<Position, Piece>();

    public Board(){
        this.initBoard();
    };


    private void initBoard() {
        ArrayList<ArrayList<Integer>> newBoard = new ArrayList<>();
        
        // Initializing board with default starting values
        for (int i = 0; i < 7; i++) {
            newBoard.add(new ArrayList<Integer>());
        }

        int[] row1 = {1,0,0,1,0,0,1};
        int[] row2 = {0,1,0,1,0,1,0};
        int[] row3 = {0,0,1,1,1,0,0};
        int[] row4 = {1,1,1,0,1,1,1};
        int[] row5 = {0,0,1,1,1,0,0};
        int[] row6 = {0,1,0,1,0,1,0};
        int[] row7 = {1,0,0,1,0,0,1};

        int[][] rows = {row1, row2, row3, row4, row5, row6, row7};

        int rowCount = 0;
        for (ArrayList<Integer> row : newBoard) {
            for (int i = 0; i < 7; i++) {
                row.add(rows[rowCount][i]);
            }
            rowCount++;
        }

        this.boardState = newBoard;

    }

    public void setBoardState(ArrayList<ArrayList<Integer>> newBoardState) {
        this.boardState = newBoardState;
    }

    public ArrayList<ArrayList<Integer>> getBoardState() {
        return this.boardState;
    }

    // private void initPosition(){
    //     positions[0] = new Position(0, 0, this);
    //     positions[1] = new Position(0, 3, this);
    //     positions[2] = new Position(0, 6, this);
    //     positions[3] = new Position(1, 1, this);
    //     positions[4] = new Position(1, 3, this);
    //     positions[5] = new Position(1, 5, this);
    //     positions[6] = new Position(2, 1, this);
    //     positions[7] = new Position(2, 2, this);
    //     positions[8] = new Position(2, 3, this);
    //     positions[9] = new Position(3, 0, this);
    //     positions[10] = new Position(3, 1, this);
    //     positions[11] = new Position(3, 2, this);
    //     positions[12] = new Position(3, 4, this);
    //     positions[13] = new Position(3, 5, this);
    //     positions[14] = new Position(3, 6, this);
    //     positions[15] = new Position(4, 1, this);
    //     positions[16] = new Position(4, 2, this);
    //     positions[17] = new Position(4, 3, this);
    //     positions[18] = new Position(5, 1, this);
    //     positions[19] = new Position(5, 3, this);
    //     positions[20] = new Position(5, 5, this);
    //     positions[21] = new Position(6, 0, this);
    //     positions[22] = new Position(6, 3, this);
    //     positions[23] = new Position(6, 6, this);

    //     rows[0] = {positions[0], positions[1], positions[2]};
    //     rows[1] = {positions[3], positions[4], positions[5]};
    //     rows[2] = {positions[6], positions[7], positions[8]};
    //     rows[3] = {positions[9], positions[10], positions[11]};
    //     rows[4] = {positions[12], positions[13], positions[14]};
    //     rows[5] = {positions[15], positions[16], positions[17]};
    //     rows[6] = {positions[18], positions[19], positions[20]};

    //     cols[0] = {positions[0], positions[9], positions[21]};
    //     cols[1] = {positions[3], positions[16], positions[18]};
    //     cols[2] = {positions[6], positions[11], positions[15]};
    //     cols[3] = {positions[1], positions[4], positions[7]};
    //     cols[4] = {positions[16], positions[19], positions[22]};
    //     cols[5] = {positions[8], positions[12], positions[17]};
    //     cols[6] = {positions[5], positions[13], positions[20]};

    //     positions[0].addNeighbors({positions[1], positions[9]});
    //     positions[1].addNeighbors({positions[0], positions[2], positions[4]});
    //     positions[2].addNeighbors({positions[1], positions[4]});
    //     positions[3].addNeighbors({positions[4], positions[10]});
    //     positions[4].addNeighbors({positions[3], positions[1], positions[5], positions[4]});
    //     positions[5].addNeighbors({positions[4], positions[13]});
    //     positions[6].addNeighbors({positions[7], positions[11]});
    //     positions[7].addNeighbors({positions[4], positions[6], positions[8]});
    //     positions[8].addNeighbors({positions[7], positions[12]});
    //     positions[9].addNeighbors({positions[0], positions[10], positions[21]});
    //     positions[10].addNeighbors({positions[3], positions[9], positions[11], positions[18]});
    //     positions[11].addNeighbors({positions[6], positions[10], positions[15]});
    //     positions[12].addNeighbors({positions[8], positions[13], positions[17]});
    //     positions[13].addNeighbors({positions[5], positions[14], positions[12], positions[20]});
    //     positions[14].addNeighbors({positions[2], positions[13], positions[23]});
    //     positions[15].addNeighbors({positions[11], positions[16]});
    //     positions[16].addNeighbors({positions[15], positions[17], positions[19]});
    //     positions[17].addNeighbors({positions[16], positions[12]});
    //     positions[18].addNeighbors({positions[10], positions[19]});
    //     positions[19].addNeighbors({positions[16], positions[18], positions[20], positions[22]});
    //     positions[20].addNeighbors({positions[13], positions[19]});
    //     positions[21].addNeighbors({positions[9], positions[22]});
    //     positions[22].addNeighbors({positions[19], positions[21], positions[23]});
    //     positions[23].addNeighbors({positions[22], positions[14]});

    // };

    // public void addPiece(Piece piece, Position position){
    //     positionOfPieces.put(piece, position);
    //     pieceOfPositions.put(position, piece);
    // };

    // public void removePiece(Position position){
    //     positionOfPieces.remove(piece);
    //     pieceOfPositions.remove(position);
    // };

    // public void movePiece(Position oldPosition, Position newPosition){
    //     if(isPositionEmpty(newPosition)){
    //         pieceOfPositions.remove(oldPosition);
    //         positionOfPieces.put(piece, position);
    //         pieceOfPositions.put(position, piece);
    //     };
    // };

    // public Boolean isPositionEmpty(Position position){
    //     return !pieceOfPositions.containsKey(position);
    // };

    // public Piece getPiece(Position position){
    //     return pieceOfPositions.get(position);
    // };
}
