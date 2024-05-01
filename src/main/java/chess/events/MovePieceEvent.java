package chess.events;

import chess.game.Board;
import chess.game.Piece;
import chess.game.PieceColor;

public class MovePieceEvent implements Event {
    private final Board result;
    private final PieceColor color;
    private final String uciString;
    public MovePieceEvent(String moveString, PieceColor moverColor, Board moveResult) {
        uciString = moveString;
        color = moverColor;
        result = moveResult;
    }
    @Override
    public String getMessage() {
        return color.toString() + " made move: " + uciString;
    }
    public Board getMoveResult() {
        return result;
    }
    public PieceColor getMoverColor() {
        return color;
    }
}
