package chess.pieces;

import chess.game.Board;
import chess.game.Move;

import java.util.List;

public class Pawn extends Piece {
    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public PieceType getType() {
        return PieceType.Pawn;
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        /*
        TODO: implement moves
         */
        return null;
    }

    @Override
    public String toString() {
        PieceColor color = getColor();
        return switch (color) {
            case White -> "P";
            case Black -> "p";
            default -> "";
        };
    }
}
