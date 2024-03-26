package com.chess;

import java.util.ArrayList;
import java.util.List;

public class Game implements GameInterface {
    private Board board;
    private List<Observer> observers;
    private PieceColor turn;
    private int fullmove;
    private int halfmove;
    private Game(Board gameBoard, PieceColor currTurn, int halfmoveClock, int fullmoveCounter) {
        board = gameBoard;
        turn = currTurn;
        fullmove = fullmoveCounter;
        halfmove = halfmoveClock;
        observers = new ArrayList<>();
    }

    public static Game NewGame() {
        return new Game(Board.NewBoard(), PieceColor.White, 0, 1);
    }
    public String castleOptions() {
        /*
        TODO: implement
         */
        return "-";
    }
    public String enPassantTargets() {
        /*
        TODO: implement
         */
        return "-";
    }
    public String activeColor() {
        return switch (turn) {
            case White -> "w";
            case Black -> "b";
            default -> "-";
        };
    }
    @Override
    public void attach(Observer newObserver) {
        observers.add(newObserver);
    }
    @Override
    public String getState() {
        return board.toString() + " "
                + activeColor() + " "
                + castleOptions() + " "
                + enPassantTargets() + " ";

    }
    @Override
    public String postMove(String moveString) { // moveString in Universal Chess Interface
        return "";
    }
}
