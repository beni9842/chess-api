package chess.observers;

import chess.events.Event;
import chess.events.MovePieceEvent;
import chess.game.*;

import java.util.ArrayList;
import java.util.List;

public class CheckObserver implements Observer {
    private Board moveResult;
    public CheckObserver() {
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
    public PieceColor inCheck() {
        List<Move> WhiteMoves = LegalMoves.GetAllLegalMoves(moveResult, PieceColor.White);
        List<Move> BlackMoves = LegalMoves.GetAllLegalMoves(moveResult, PieceColor.Black);
        for (Move move : WhiteMoves) {
            Piece capturedPiece = move.capturedPiece(moveResult);
            if (capturedPiece.equals(Pieces.BlackKing())) {
                return PieceColor.Black;
            }
        }
        for (Move move : BlackMoves) {
            Piece capturedPiece = move.capturedPiece(moveResult);
            if (capturedPiece.equals(Pieces.WhiteKing())) {
                return PieceColor.White;
            }
        }
        return PieceColor.NoColor;
    }
}
