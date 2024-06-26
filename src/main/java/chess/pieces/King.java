package chess.pieces;

import chess.game.Board;
import chess.game.Game;
import chess.game.GameObserver;
import chess.moves.Castle;
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
    public List<Move> getMoves(Board target, int file, int rank, String epString, String castleString) {
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
        if (color == PieceColor.White && !GameObserver.InCheck(PieceColor.White, target, "-", "-")) {
            if (castleString.contains("K")) {
                // check for white king-side castling
                boolean kingSide = true;
                for (int i = file+1; i < 7; i++) {
                    if (!target.getPiece(i, rank).equals(Pieces.NoPiece())) {
                        kingSide = false;
                        break;
                    }
                    Board result = new Move(file, rank, i, rank, target).execute();
                    if (GameObserver.InCheck(PieceColor.White, result, "-", "-")) {
                        kingSide = false;
                        break;
                    }
                }
                if (kingSide) {
                    moves.add(new Castle(file, rank, file+2, rank, target));
                }
            }
            if (castleString.contains("Q")) {
                // check for white queen-side castling
                boolean queenSide = true;
                for (int i = file-1; i > 0; i--) {
                    if (!target.getPiece(i, rank).equals(Pieces.NoPiece())) {
                        queenSide = false;
                        break;
                    }
                    Board result = new Move(file, rank, i, rank, target).execute();
                    if (GameObserver.InCheck(PieceColor.White, result, "-", "-")) {
                        queenSide = false;
                        break;
                    }
                }
                if (queenSide) {
                    moves.add(new Castle(file, rank, file-2, rank, target));
                }
            }
        } else {
            if (castleString.contains("k")) {
                // check for black king-side castling
                boolean kingSide = true;
                for (int i = file+1; i < 7; i++) {
                    if (!target.getPiece(i, rank).equals(Pieces.NoPiece())) {
                        kingSide = false;
                        break;
                    }
                    Board result = new Move(file, rank, i, rank, target).execute();
                    if (GameObserver.InCheck(PieceColor.Black, result, "-", "-")) {
                        kingSide = false;
                        break;
                    }
                }
                if (kingSide) {
                    moves.add(new Castle(file, rank, file+2, rank, target));
                }
            }
            if (castleString.contains("q")) {
                // check for black queen-side castling
                boolean queenSide = true;
                for (int i = file-1; i > 0; i--) {
                    if (!target.getPiece(i, rank).equals(Pieces.NoPiece())) {
                        queenSide = false;
                        break;
                    }
                    Board result = new Move(file, rank, i, rank, target).execute();
                    if (GameObserver.InCheck(PieceColor.Black, result, "-", "-")) {
                        queenSide = false;
                        break;
                    }
                }
                if (queenSide) {
                    moves.add(new Castle(file, rank, file-2, rank, target));
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
