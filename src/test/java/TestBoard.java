import com.chess.Board;
import com.chess.Piece;
import com.chess.Pieces;
import org.junit.jupiter.api.Test;

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
}
