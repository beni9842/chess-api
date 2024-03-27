package com.observers;

import com.game.GameInterface;
import com.events.Event;

public interface Observer {
    void observe(GameInterface game);
    void update(Event e);
}
