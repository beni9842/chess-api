package chess.moves;

import chess.game.Board;
import chess.game.GameObserver;
import chess.pieces.Piece;
import chess.pieces.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class LegalMoves {
    public static List<Move> GetLegalMoves(Board board, PieceColor activeColor, String epTarget, String castleRights) {
        List<Move> legalMoves = new ArrayList<>();
        for (int file = 0; file < 8; file++) {
            for (int rank = 0; rank < 8; rank++) {
                Piece currPiece = board.getPiece(file, rank);
                // only consider moves for the active color
                if (currPiece.getColor() == activeColor) {
                    List<Move> currPieceMoves = currPiece.getMoves(board, file, rank, epTarget, castleRights);
                    // loop through moves for a given piece and remove the moves that leave the moving color in check
                    for (Move currMove : currPieceMoves) {
                        // copy the board to prevent test moves from executing on the actual board
                        Board result = currMove.execute();
                        if (!GameObserver.InCheck(activeColor, result, epTarget, castleRights)) {
                            legalMoves.add(currMove);
                        }
                    }
                }
            }
        }
        return legalMoves;
    }
}
