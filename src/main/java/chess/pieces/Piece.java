package chess.pieces;

import chess.game.Board;
import chess.moves.Move;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private PieceColor color;
    public Piece(PieceColor pieceColor) {
        color = pieceColor;
    }
    public PieceColor getColor() {
        return color;
    };
    @Override
    public boolean equals(Object o) {
        if (o instanceof Piece) {
            return Objects.equals(this.toString(), o.toString());
        } else {
            return false;
        }
    }
    public abstract PieceType getType();
    public abstract Piece copy();
    public abstract List<Move> getMoves(Board target, int file, int rank, String epString, String castleString);
    public abstract int getValue();
    @Override
    public abstract String toString();
    public abstract String toSAN();
}
