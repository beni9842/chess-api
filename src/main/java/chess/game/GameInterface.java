package chess.game;

import chess.events.Event;
import chess.observers.Observer;

public interface GameInterface {
    String getState();
    String postMove(String moveString);
    void attach(Observer newObserver);
    void broadcast(Event e);
    int getID();
}
