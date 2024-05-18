package chess.pieces;

import chess.game.Board;
import chess.game.Move;

import java.util.List;

public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public PieceType getType() {
        return PieceType.Queen;
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        return null;
    }

    @Override
    public String toString() {
        PieceColor color = getColor();
        return switch (color) {
            case White -> "Q";
            case Black -> "q";
            default -> "";
        };
    }
}
