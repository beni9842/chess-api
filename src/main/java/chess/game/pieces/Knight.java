package chess.pieces;

import chess.game.Board;
import chess.moves.Move;

import java.util.ArrayList;
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
    public Piece copy() {
        return new Knight(getColor());
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank, String epString, String castleString) {
        List<Move> moves = new ArrayList<>();
        PieceColor color = getColor();
        // Check all possible knight moves
        int[] rankOffsets = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] fileOffsets = {-1, 1, -2, 2, -2, 2, -1, 1};
        // 8 possible moves at most
        for (int i = 0; i < 8; i++) {
            int dstRank = rank + rankOffsets[i];
            int dstFile = file + fileOffsets[i];
            if (dstRank >= 0 && dstRank < 8 && dstFile >= 0 && dstFile < 8) {
                Move newMove = new Move(file, rank, dstFile, dstRank, target);
                if (newMove.getCapturedPiece().getColor() != color) {
                    moves.add(newMove);
                }
            }
        }
        return moves;
    }

    @Override
    public int getValue() {
        return 3;
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

    @Override
    public String toSAN() {
        return "N";
    }
}
