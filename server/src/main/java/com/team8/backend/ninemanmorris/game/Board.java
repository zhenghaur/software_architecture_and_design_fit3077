package com.team8.backend.ninemanmorris.game;

import java.util.ArrayList;

import com.team8.backend.ninemanmorris.enums.Token;
import com.team8.backend.ninemanmorris.positions.*;

// Get at position
public class Board {

    Position[][] boardState = new Position[7][7];
    // All Public Positions
    ArrayList<PublicPosition> publicPositions;

    /***
     * 
     * @param initialBoard Matrix of ints representing the defualt board state
     */
    public Board(int[][] initialBoard) {
        this.initBoard(initialBoard);
    };

    /***
     * Board Builder with position population and piece
     * 
     * @param positions initial int version being converted and adapted for Position
     *                  array
     */
    private void initBoard(int[][] positions) {
        // ArrayList of Public positions
        ArrayList<PublicPosition> validPosition = new ArrayList<PublicPosition>();

        for (int row = 0; row < positions.length; row++) {
            for (int col = 0; col < positions[row].length; col++) {
                int positionValue = positions[row][col];

                // First case that It is a non valid tile
                if (positionValue == Token.EMPTY.getToken()) {
                    boardState[row][col] = new PrivatePosition(row, col);
                } else {
                    // Creates a new Public position
                    PublicPosition newPosition = new PublicPosition(row, col);
                    if (positionValue == Token.TILE.getToken()) {
                        boardState[row][col] = newPosition;
                    } else if (positionValue == Token.PLAYER_1.getToken()) {
                        newPosition.setPlayerOne();
                        boardState[row][col] = newPosition;
                    } else if (positionValue == Token.PLAYER_2.getToken()) {
                        newPosition.setPlayerTwo();
                        boardState[row][col] = newPosition;
                    }
                    // Adding the public position
                    validPosition.add(newPosition);
                }
            }
        }
        // Setting publicPositions
        this.publicPositions = validPosition;
        // Populate all positions with
        this.populateNeighbours(validPosition);
    }

    /***
     * Populating all locations to have their valid neighbours
     */
    private void populateNeighbours(ArrayList<PublicPosition> validPosition) {
        // Loops through every valid Position
        for (PublicPosition position : validPosition) {
            // Loops through every found neighbour and appends
            for (Position neighbour : this.populateNeighbourHelper(position.getRowIndex(), position.getColIndex())) {
                position.addNeighbour(neighbour);
            }
        }
    }

    /***
     * For identifying first neighbour on N E S W Respectively, and returning
     * ArrayList containing as such
     * 
     * @param rowIndex - row start Index to check from
     * @param colIndex - col start Index to check from
     * @return ArrayList of all neighbouring positions
     */
    private ArrayList<Position> populateNeighbourHelper(int rowIndex, int colIndex) {
        ArrayList<Position> neighbours = new ArrayList<>();

        // Checks North for neighbour
        for (int row = rowIndex - 1; row >= 0; row--) {
            Position northNeighbour = this.boardState[row][colIndex];
            if (row == 3 & colIndex == 3) {
                break;
            }
            if (northNeighbour.getToken() != Token.EMPTY) {
                neighbours.add(northNeighbour);
                break;
            }
        }

        // Checks East for neighbour
        for (int col = colIndex + 1; col < this.boardState[0].length; col++) {
            Position eastNeighbour = this.boardState[rowIndex][col];
            if (col == 3 & rowIndex == 3) {
                break;
            }
            if (eastNeighbour.getToken() != Token.EMPTY) {
                neighbours.add(eastNeighbour);
                break;
            }
        }

        // Checks South for neighbour
        for (int row = rowIndex + 1; row < this.boardState.length; row++) {
            Position southNeighbour = this.boardState[row][colIndex];
            if (row == 3 & colIndex == 3) {
                break;
            }
            if (southNeighbour.getToken() != Token.EMPTY) {
                neighbours.add(southNeighbour);
                break;
            }
        }

        // Checks West for neighbour
        for (int col = colIndex - 1; col >= 0; col--) {
            Position westNeighbour = this.boardState[rowIndex][col];
            if (col == 3 & rowIndex == 3) {
                break;
            }
            if (westNeighbour.getToken() != Token.EMPTY) {
                neighbours.add(westNeighbour);
                break;
            }
        }

        // returning a list of all neighbours
        return neighbours;
    }

    /**
     * Getter for the current BoardState
     * 
     * @return the boardState
     */
    public Position[][] getBoardState() {
        return this.boardState;
    }

    /**
     * Getter for PublicPositions
     * 
     * @return
     */
    public ArrayList<PublicPosition> getPublicPositions() {
        return this.publicPositions;
    }

    /***
     * Obtains the token @ an input row, col coordinate
     * 
     * @param rowIndex - rowIndex coordinate
     * @param colIndex - colIndex coordinate
     * @return - Token returned at the position
     */
    public Token getTokenAtCoord(int rowIndex, int colIndex) {
        return this.boardState[rowIndex][colIndex].getToken();
    }

    /**
     * Makes a move given a from pair of indexes and a to pair of indexs
     * 
     * @param fromRow - row index of from
     * @param fromCol - col index of from
     * @param toRow   - row index of to
     * @param toCol   - col index of to
     */
    public void makeMoveSlide(int fromRow, int fromCol, int toRow, int toCol) {
        Token fromToken = this.getTokenAtCoord(fromRow, fromCol);
        for (PublicPosition position : this.publicPositions) {
            if (position.getRowIndex() == fromRow & position.getColIndex() == fromCol) {
                position.removePlayer();
            } else if (position.getRowIndex() == toRow & position.getColIndex() == toCol) {
                if (fromToken == Token.PLAYER_1) {
                    position.setPlayerOne();
                } else if (fromToken == Token.PLAYER_2) {
                    position.setPlayerTwo();
                }
            }
        }
    }

    /**
     * Overload for the case of placing a token at a location
     * 
     * @param row         - row index to be placed
     * @param col         - col index to be placed
     * @param playerToken - player token to be placed
     */
    public void makeMovePlace(int row, int col, Token playerToken) {
        for (PublicPosition position : this.publicPositions) {
            if (position.getRowIndex() == row && position.getColIndex() == col) {
                if (playerToken == Token.PLAYER_1) {
                    position.setPlayerOne();
                } else if (playerToken == Token.PLAYER_2) {
                    position.setPlayerTwo();
                }
            }
        }
    }

    /**
     * Overload for the case of removing a token at a location
     * 
     * @param row - row index to be remived
     * @param col - col index to be placed
     */
    public void makeMoveDelete(int row, int col) {
        for (PublicPosition position : this.publicPositions) {
            if (position.getRowIndex() == row && position.getColIndex() == col) {
                position.removePlayer();
            }
        }
    }

    /***
     * Getting the board state
     * 
     * @return return of the board state in interger form
     */
    public Integer[][] getBoardStateInt() {
        Integer[][] boardStateInt = new Integer[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                boardStateInt[i][j] = this.boardState[i][j].getToken().getToken();
            }
        }
        return boardStateInt;
    }

    /***
     * For Debgging Purposes
     */
    public void printBoard() {
        for (Position[] row : this.boardState) {
            for (Position position : row) {
                System.out.print(position.getToken().getToken() + " ");
            }
            System.out.println("");
        }
    }
}
