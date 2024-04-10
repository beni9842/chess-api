package chess.game;

import chess.events.Event;
import chess.observers.Observer;

public interface GameInterface {
    /*
    GameInterface: interface that defines the public functionality of a game from the perspective of the API backend.
    methods attached will be strictly limited to the functions necessary to interact with a chess game, abstracting
    away the details so that there are not to many methods available to the API or any other class that will be using
    the game interface.
     */

    String getState();
    /*
    getState(): returns the FEN String representation of the board
    (eg: "nbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1")
     */
    String postMove(String moveString);
    /*
    pstMove(): accepts a UCI String representation for a move and if that move is valid (the piece in play is the
    correct turn and can move to the destination on that turn), the move is applied to the game and the turn is updated,
    otherwise nothing happens. The String back should represent if the move happened, if any captures were made, and if
    the move resulted in check or checkmate.
     */
    int getID();
    /*
    returns the game ID. All games have IDs which are unique and statically created by the GameID class
     */
}
