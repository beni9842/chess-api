import chess.game.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoard {
    @Test
    public void testNewBoard() {
        Board newBoard = Board.NewBoard();
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", newBoard.toString());
    }
    @Test
    public void testKingsPawnOpening() {
        Board b = Board.NewBoard();
        b.movePiece(4, 6, 4, 4);
        assertEquals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR", b.toString());
    }
    @Test
    public void testRankString() {
        assertEquals("8", Board.RankString(0));
        assertEquals("1", Board.RankString(7));
    }
    @Test
    public void testFileString() {
        assertEquals("a", Board.FileString(0));
        assertEquals("h", Board.FileString(7));
    }
    @Test
    public void testRankInt() {
        assertEquals(0, Board.RankInt("8"));
        assertEquals(7, Board.RankInt("1"));
    }
    @Test
    public void testFileInt() {
        assertEquals(0, Board.FileInt("a"));
        assertEquals(7, Board.FileInt("h"));
    }
}
