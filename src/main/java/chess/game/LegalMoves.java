package chess.game;

import java.util.ArrayList;
import java.util.List;

public abstract class LegalMoves {
    public static List<Move> GetAll(Board b, PieceColor turn) {
        List<Move> moveList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = b.getPiece(i, j);
                if (p.getColor() == turn) {
                    moveList.addAll(GetMovesForPiece(b, i, j));
                }
            }
        }
        return moveList;
    }
    public static List<Move> GetMovesForPiece(Board b, int rank, int file) {
        Piece p = b.getPiece(rank, file);
        PieceType type = p.getType();
        PieceColor color = p.getColor();
        return switch (type) {
            case Pawn -> GetPawnMoves(b, rank, file, color);
            case Rook -> GetRookMoves(b, rank, file, color);
            case Knight -> GetKnightMoves(b, rank, file, color);
            case Bishop -> GetBishopMoves(b, rank, file, color);
            case Queen -> GetQueenMoves(b, rank, file, color);
            case King -> GetKingMoves(b, rank, file, color);
            case NoType -> new ArrayList<>();
        };
    }

    private static List<Move> GetKingMoves(Board b, int rank, int file, PieceColor color) {
        /*
        TODO: implement
         */
        return new ArrayList<>();
    }

    private static List<Move> GetQueenMoves(Board b, int rank, int file, PieceColor color) {
        /*
        TODO: implement
         */
        return new ArrayList<>();
    }

    private static List<Move> GetBishopMoves(Board b, int rank, int file, PieceColor color) {
        /*
        TODO: implement
         */
        return new ArrayList<>();
    }

    private static List<Move> GetKnightMoves(Board b, int rank, int file, PieceColor color) {
        /*
        TODO: implement
         */
        return new ArrayList<>();
    }

    private static List<Move> GetRookMoves(Board b, int rank, int file, PieceColor color) {
        /*
        TODO: implement
         */
        return new ArrayList<>();
    }

    private static List<Move> GetPawnMoves(Board b, int rank, int file, PieceColor color) {
        /*
        TODO: implement
         */
        return new ArrayList<>();
    }
}
