package chess.pieces;

import chess.game.Board;
import chess.game.Move;

import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public PieceType getType() {
        return PieceType.Knight;
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        return null;
    }

    @Override
    public String toString() {
        PieceColor color = getColor();
        return switch (color) {
            case White -> "N";
            case Black -> "n";
            default -> "";
        };
    }
}
