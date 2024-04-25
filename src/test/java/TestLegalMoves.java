import chess.game.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLegalMoves {

    @Test
    void testGetAll() {
        Board board = Board.NewBoard();
        List<Move> allMoves = LegalMoves.GetAll(board, PieceColor.White);
        assertEquals(20, allMoves.size());
    }

    @Test
    void testGetMovesForPiece() {
        Board board = Board.NewBoard();
        List<Move> moves = LegalMoves.GetMovesForPiece(board, 1, 0);
        assertEquals(2, moves.size());
    }

    @Test
    void testGetKingMoves() {
        // Add meaningful test cases for GetKingMoves()
        Board board1 = Board.EmptyBoard();
        board1.setPiece(Pieces.WhiteKing(), 4, 4);
        List<Move> moves = LegalMoves.GetKingMoves(board1, 4, 4, PieceColor.White);
        assertEquals(8, moves.size());
        board1.setPiece(Pieces.WhitePawn(), 3, 4);
        moves = LegalMoves.GetKingMoves(board1, 4, 4, PieceColor.White);
        assertEquals(7, moves.size());
    }

    @Test
    void testGetQueenMoves() {
        // Add meaningful test cases for GetQueenMoves()
    }

    @Test
    void testGetBishopMoves() {
        // Add meaningful test cases for GetBishopMoves()

    }

    @Test
    void testGetKnightMoves() {
        // Add meaningful test cases for GetKnightMoves()
    }

    @Test
    void testGetRookMoves() {
        // Add meaningful test cases for GetRookMoves()
    }

    @Test
    void testGetPawnMoves() {
        // Add meaningful test cases for GetPawnMoves()
    }
}
