package chess.pieces;

import chess.game.Board;
import chess.game.Move;

import java.util.List;

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
            Piece otherPiece = (Piece) o;
            return otherPiece.getType() == this.getType() && otherPiece.getColor() == this.getColor();
        } else {
            return false;
        }
    }
    public abstract PieceType getType();
    public abstract List<Move> getMoves(Board target, int file, int rank);
    @Override
    public abstract String toString();
}
