package com.team8.backend.ninemanmorris;

public class Main {
    public static void main(String[] args) {
        System.out.println("JHE");

        Board board = new Board();
        for (int i = 0; i < board.getBoardState().size(); i++) {
            System.out.println(board.getBoardState().get(i));
        }
    }
}
