package chess.game;

import chess.moves.LegalMoves;
import chess.moves.Move;
import chess.pieces.Piece;
import chess.pieces.PieceColor;
import chess.pieces.PieceType;

import java.util.List;

public abstract class GameObserver {
    public static boolean InCheck(PieceColor color, Board subject, String epString, String castleString) {
        for (int file = 0; file < 8; file++) {
            for (int rank = 0; rank < 8; rank++) {
                Piece currPiece = subject.getPiece(file, rank);
                if (currPiece.getColor() != color) {
                    List<Move> currPieceMoves = currPiece.getMoves(subject, file, rank, epString, castleString);
                    for (Move currMove : currPieceMoves) {
                        Piece capturedPiece = currMove.getCapturedPiece();
                        if (capturedPiece.getType() == PieceType.King && capturedPiece.getColor() == color) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean InCheckmate(PieceColor color, Board subject, String epString, String castleString) {
        return LegalMoves.GetLegalMoves(subject, color, epString, castleString).isEmpty();
    }
}
