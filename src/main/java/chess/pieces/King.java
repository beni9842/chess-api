package chess.pieces;

import chess.game.Board;
import chess.moves.Move;

import java.util.ArrayList;
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
    public Piece copy() {
        return new King(getColor());
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        List<Move> moves = new ArrayList<>();
        PieceColor color = getColor();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int dstRank = rank + i;
                int dstFile = file + j;
                if (dstRank >= 0 && dstRank < 8 && dstFile >= 0 && dstFile < 8 && !(rank == dstRank && file == dstFile)) {
                    Move newMove = new Move(file, rank, dstFile, dstRank, target);
                    if (newMove.getCapturedPiece().getColor() != color) {
                        moves.add(newMove);
                    }
                }
            }
        }
        return moves;
    }

    @Override
    public int getValue() {
        return 0;
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

    @Override
    public String toSAN() {
        return "K";
    }
}
