import chess.game.Board;
import chess.game.Piece;
import chess.game.Pieces;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {
    @Test
    void testNewBoard() {
        Board newBoard = Board.NewBoard();
        assert newBoard.getPiece(2,2).equals(Pieces.NoPiece());
    }

    @Test
    void testRemovePiece() {
        Board b = Board.NewBoard();
        Piece p = b.removePiece(1,2);
        assert p.equals(Pieces.BlackPawn());
        assert Pieces.NoPiece().equals(b.getPiece(1, 2));
    }

    @Test
    void testMovePieceRoutine() {
        Board b = Board.NewBoard();
        Piece p = b.removePiece(1,2);
        b.setPiece(p, 1+2, 2);
        assert Pieces.NoPiece().equals(b.getPiece(1, 2));
        assert b.getPiece(3, 2).equals(Pieces.BlackPawn());
    }

    @Test
    void testToString() {
        Board b = Board.NewBoard();
        assert b.toString().equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }

    @Test
    void testFileInt() {
        assert Board.FileInt('a') == 0;
        assert Board.FileInt('h') == 7;
    }

    @Test
    void testRankInt() {
        assert Board.RankInt('1') == 7;
        assert Board.RankInt('8') == 0;
    }

    @Test
    void testPrivateConstructor() {
        assertThrows(IllegalAccessException.class, Board.class::newInstance);
    }


    @Test
    void testValidIndices() {
        assertTrue(Board.validIndices(0, 0));
        assertTrue(Board.validIndices(7, 7));
        assertFalse(Board.validIndices(-1, 0));
        assertFalse(Board.validIndices(8, 0));
        assertFalse(Board.validIndices(0, -1));
        assertFalse(Board.validIndices(0, 8));
    }
}
