package chess.pieces;

import chess.game.Board;
import chess.moves.Move;

import java.util.List;

public class NoPiece extends Piece {

    public NoPiece() {
        super(PieceColor.NoColor);
    }

    @Override
    public PieceType getType() {
        return PieceType.NoType;
    }

    @Override
    public Piece copy() {
        return new NoPiece();
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank, String epString, String castleString) {
        return List.of();
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public String toString() {
        return " ";
    }

    @Override
    public String toSAN() {
        return "";
    }
}
