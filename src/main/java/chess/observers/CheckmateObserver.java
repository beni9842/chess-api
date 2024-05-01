package chess.observers;

import chess.events.Event;
import chess.events.MovePieceEvent;
import chess.game.*;

import java.util.List;

public class CheckmateObserver implements Observer {
    private Board moveResult;
    public CheckmateObserver() {
        moveResult = Board.EmptyBoard();
    }
    @Override
    public void update(Event e) {
        if (e instanceof MovePieceEvent) {
            MovePieceEvent moveEvent = (MovePieceEvent) e;
            setMoveResult(moveEvent.getMoveResult());
        }
    }
    public void setMoveResult(Board b) {
        moveResult = b;
    }
    public PieceColor inCheckmate() {
        List<Move> WhiteMoves = LegalMoves.GetAllLegalMoves(moveResult, PieceColor.White);
        List<Move> BlackMoves = LegalMoves.GetAllLegalMoves(moveResult, PieceColor.Black);
        if (WhiteMoves.isEmpty()) {
            return PieceColor.White;
        }
        if (BlackMoves.isEmpty()) {
            return PieceColor.Black;
        }
        return PieceColor.NoColor;
    }
}
