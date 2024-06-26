package chess.pieces;

import chess.game.Board;
import chess.moves.Move;
import chess.moves.PawnDouble;

import java.util.ArrayList;
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
    public Piece copy() {
        return new Pawn(getColor());
    }

    @Override
    public List<Move> getMoves(Board target, int file, int rank) {
        List<Move> moves = new ArrayList<>();
        PieceColor color = getColor();
        if (color == PieceColor.Black) {
            Move m1 = new Move(file, rank, file, rank+1, target);
            if (m1.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(m1);
                if (rank == 1) {
                    Move m2 = new PawnDouble(file, rank, file, rank+2, target);
                    if (m2.getCapturedPiece().equals(Pieces.NoPiece())) {
                        moves.add(m2);
                    }
                }
            }
            if (file > 0) {
                Move attack1 = new Move(file, rank, file-1, rank+1, target);
                if (attack1.getCapturedPiece().getColor() == PieceColor.White) {
                    moves.add(attack1);
                }
            }
            if (file < 7) {
                Move attack2 = new Move(file, rank, file+1, rank+1, target);
                if (attack2.getCapturedPiece().getColor() == PieceColor.White) {
                    moves.add(attack2);
                }
            }
        } else { // Color is assumed to be white
            Move m1 = new Move(file, rank, file, rank-1, target);
            if (m1.getCapturedPiece().equals(Pieces.NoPiece())) {
                moves.add(m1);
                if (rank == 6) {
                    Move m2 = new PawnDouble(file, rank, file, rank-2, target);
                    if (m2.getCapturedPiece().equals(Pieces.NoPiece())) {
                        moves.add(m2);
                    }
                }
            }
            if (file > 0) {
                Move attack1 = new Move(file, rank, file-1, rank-1, target);
                if (attack1.getCapturedPiece().getColor() == PieceColor.Black) {
                    moves.add(attack1);
                }
            }
            if (file < 7) {
                Move attack2 = new Move(file, rank, file+1, rank-1, target);
                if (attack2.getCapturedPiece().getColor() == PieceColor.Black) {
                    moves.add(attack2);
                }
            }
        }
        return moves;
    }

    @Override
    public int getValue() {
        return 1;
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

    @Override
    public String toSAN() {
        return "";
    }
}
