package chess.pieces;

import chess.game.Board;
import chess.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public PieceType getType() {
        return PieceType.Bishop;
    }

    @Override
    public Piece copy() {
        return new Bishop(getColor());
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        List<Move> moves = new ArrayList<>();
        PieceColor color = getColor();
        // get NW moves
        int dstRank = rank - 1;
        int dstFile = file - 1;
        Move newMove;
        while (dstRank >= 0 && dstFile >= 0) {
            newMove = new Move(file, rank, dstFile, dstRank, target);
            if (newMove.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(newMove);
            } else if (newMove.getCapturedPiece().getColor() != color) {
                moves.add(newMove);
                break;
            } else {
                break;
            }
            dstRank--;
            dstFile--;
        }
        // get NE moves
        dstRank = rank - 1;
        dstFile = file + 1;
        while (dstRank >= 0 && dstFile < 8) {
            newMove = new Move(file, rank, dstFile, dstRank, target);
            if (newMove.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(newMove);
            } else if (newMove.getCapturedPiece().getColor() != color) {
                moves.add(newMove);
                break;
            } else {
                break;
            }
            dstRank--;
            dstFile++;
        }
        // get SE moves
        dstRank = rank + 1;
        dstFile = file + 1;
        while (dstRank < 8 && dstFile < 8) {
            newMove = new Move(file, rank, dstFile, dstRank, target);
            if (newMove.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(newMove);
            } else if (newMove.getCapturedPiece().getColor() != color) {
                moves.add(newMove);
                break;
            } else {
                break;
            }
            dstRank++;
            dstFile++;
        }
        // get SW moves
        dstRank = rank + 1;
        dstFile = file - 1;
        while (dstRank < 8 && dstFile >= 0) {
            newMove = new Move(file, rank, dstFile, dstRank, target);
            if (newMove.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(newMove);
            } else if (newMove.getCapturedPiece().getColor() != color) {
                moves.add(newMove);
                break;
            } else {
                break;
            }
            dstRank++;
            dstFile--;
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
            case White -> "B";
            case Black -> "b";
            default -> "";
        };
    }

    @Override
    public String toSAN() {
        return "B";
    }
}
