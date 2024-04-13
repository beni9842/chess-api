package chess.observers;

import chess.game.GameInterface;
import chess.events.Event;

public interface Observer {
    void observe(GameInterface game);
    void update(Event e);
}
