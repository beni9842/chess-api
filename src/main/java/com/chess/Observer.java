package com.chess;

public interface Observer {
    void observe(GameInterface game);
    void update(Event e);
}
