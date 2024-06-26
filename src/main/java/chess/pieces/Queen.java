package chess.pieces;

import chess.game.Board;
import chess.moves.Move;

import java.util.ArrayList;
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
    public Piece copy() {
        return new Queen(getColor());
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        PieceColor color = getColor();
        Piece bishop = new Bishop(color);
        Piece rook = new Rook(color);
        List<Move> moves = new ArrayList<>();
        moves.addAll(bishop.getMoves(target, file, rank));
        moves.addAll(rook.getMoves(target, file, rank));
        return moves;
    }

    @Override
    public int getValue() {
        return 9;
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

    @Override
    public String toSAN() {
        return "Q";
    }
}
