package com.chess;

public interface GameInterface {
    String getState();
    String postMove(String moveString);
    void attach(Observer newObserver);
}
