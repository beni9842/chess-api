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

        allMoves = LegalMoves.GetAll(board, PieceColor.Black);
        assertEquals(20, allMoves.size());
    }

    @Test
    void testGetMovesForPiece() {
        Board board = Board.NewBoard();
        List<Move> moves = LegalMoves.GetMovesForPiece(board, 1, 0);
        assertEquals(2, moves.size());

        moves = LegalMoves.GetMovesForPiece(board, 0, 0);
        assertEquals(0, moves.size());
    }

    @Test
    void testGetKingMoves() {
        Board board1 = Board.EmptyBoard();
        board1.setPiece(Pieces.WhiteKing(), 4, 4);
        List<Move> moves = LegalMoves.GetKingMoves(board1, 4, 4, PieceColor.White);
        assertEquals(8, moves.size());
        board1.setPiece(Pieces.WhitePawn(), 3, 4);
        moves = LegalMoves.GetKingMoves(board1, 4, 4, PieceColor.White);
        assertEquals(7, moves.size());

        Board board2 = Board.EmptyBoard();
        board2.setPiece(Pieces.BlackKing(), 0, 0);
        moves = LegalMoves.GetKingMoves(board2, 0, 0, PieceColor.Black);
        assertEquals(3, moves.size());
    }

    @Test
    void testGetQueenMoves() {
        Board board = Board.EmptyBoard();
        board.setPiece(Pieces.WhiteQueen(), 4, 4);
        List<Move> moves = LegalMoves.GetQueenMoves(board, 4, 4, PieceColor.White);
        assertEquals(27, moves.size());

        board.setPiece(Pieces.BlackPawn(), 3, 3);
        moves = LegalMoves.GetQueenMoves(board, 4, 4, PieceColor.White);
        assertEquals(24, moves.size());
    }

    @Test
    void testGetBishopMoves() {
        Board board = Board.EmptyBoard();
        board.setPiece(Pieces.WhiteBishop(), 4, 4);
        List<Move> moves = LegalMoves.GetBishopMoves(board, 4, 4, PieceColor.White);
        assertEquals(13, moves.size());

        board.setPiece(Pieces.BlackPawn(), 5, 5);
        moves = LegalMoves.GetBishopMoves(board, 4, 4, PieceColor.White);
        assertEquals(11, moves.size());
    }

    @Test
    void testGetKnightMoves() {
        Board board = Board.EmptyBoard();
        board.setPiece(Pieces.WhiteKnight(), 4, 4);
        List<Move> moves = LegalMoves.GetKnightMoves(board, 4, 4, PieceColor.White);
        assertEquals(8, moves.size());

        board.setPiece(Pieces.WhitePawn(), 5, 6);
        moves = LegalMoves.GetKnightMoves(board, 4, 4, PieceColor.White);
        assertEquals(7, moves.size());
    }

    @Test
    void testGetRookMoves() {
        Board board = Board.EmptyBoard();
        board.setPiece(Pieces.WhiteRook(), 4, 4);
        List<Move> moves = LegalMoves.GetRookMoves(board, 4, 4, PieceColor.White);
        assertEquals(14, moves.size());

        board.setPiece(Pieces.WhitePawn(), 4, 6);
        moves = LegalMoves.GetRookMoves(board, 4, 4, PieceColor.White);
        assertEquals(12, moves.size());
    }

    @Test
    void testGetPawnMoves() {
        Board board = Board.NewBoard();
        List<Move> moves = LegalMoves.GetPawnMoves(board, 1, 0, PieceColor.Black);
        assertEquals(2, moves.size());

        moves = LegalMoves.GetPawnMoves(board, 6, 0, PieceColor.White);
        assertEquals(2, moves.size());

        board.setPiece(Pieces.WhitePawn(), 3, 4);
        moves = LegalMoves.GetPawnMoves(board, 1, 4, PieceColor.Black);
        assertEquals(1, moves.size());
    }
}