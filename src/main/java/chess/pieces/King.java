package chess.pieces;

import chess.game.Board;
import chess.game.Move;

import java.util.List;

public class King extends Piece {

    public King(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public PieceType getType() {
        return PieceType.King;
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        return null;
    }

    @Override
    public String toString() {
        PieceColor color = getColor();
        return switch (color) {
            case White -> "K";
            case Black -> "k";
            default -> "";
        };
    }
}
