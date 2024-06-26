import chess.game.Board;
import chess.moves.Move;
import chess.moves.PawnDouble;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMove {
    @Test
    void testMoveToUCI() {
        Move m = new Move(3, 6, 3, 4, Board.NewBoard());
        assertEquals("d2d4", m.toUCI());
    }
    @Test
    void testMoveToSAN() {
        Move m = new Move(3, 6, 3, 4, Board.NewBoard());
        assertEquals("d4", m.toSAN());
    }
    @Test
    void testMoveExecute() {
        Board b = Board.NewBoard();
        Move move = new Move(3, 6, 3, 4, Board.NewBoard());
        b = move.execute();
        assertEquals("rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR", b.toString());
    }
}