package chess.pieces;

import chess.game.Board;
import chess.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public PieceType getType() {
        return PieceType.Rook;
    }

    @Override
    public Piece copy() {
        return new Rook(getColor());
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        List<Move> moves = new ArrayList<>();
        PieceColor color = getColor();
        // get N moves
        int dstRank = rank-1;
        int dstFile= file;
        while (dstRank >= 0) {
            Move newMove = new Move(file, rank, dstFile, dstRank, target);
            if (newMove.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(newMove);
            } else if (newMove.getCapturedPiece().getColor() != color) {
                moves.add(newMove);
                break;
            } else {
                break;
            }
            dstRank--;
        }
        // get W moves
        dstRank = rank;
        dstFile = file-1;
        while (dstFile >= 0) {
            Move newMove = new Move(file, rank, dstFile, dstRank, target);
            if (newMove.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(newMove);
            } else if (newMove.getCapturedPiece().getColor() != color) {
                moves.add(newMove);
                break;
            } else {
                break;
            }
            dstFile--;
        }
        // get S moves
        dstRank = rank+1;
        dstFile = file;
        while (dstRank < 8) {
            Move newMove = new Move(file, rank, dstFile, dstRank, target);
            if (newMove.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(newMove);
            } else if (newMove.getCapturedPiece().getColor() != color) {
                moves.add(newMove);
                break;
            } else {
                break;
            }
            dstRank++;
        }
        // get E moves
        dstRank = rank;
        dstFile = file+1;
        while (dstFile < 8) {
            Move newMove = new Move(file, rank, dstFile, dstRank, target);
            if (newMove.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(newMove);
            } else if (newMove.getCapturedPiece().getColor() != color) {
                moves.add(newMove);
                break;
            } else {
                break;
            }
            dstFile++;
        }
        return moves;
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public String toString() {
        PieceColor color = getColor();
        return switch (color) {
            case White -> "R";
            case Black -> "r";
            default -> "";
        };
    }

    @Override
    public String toSAN() {
        return "R";
    }
}
