import chess.game.Board;
import chess.game.Move;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestMove {
    @Test
    void testMoveInit() {
        Move m = new Move("d2d4");
        assert m.getMoveVector().equals(Arrays.asList(3, 6, 3, 4));
    }
    @Test
    void testMoveExecute() {
        Board b = Board.NewBoard();
        Move move = new Move("d2d4");
        move.execute(b);
        assert b.toString().equals("rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR");
    }

    @Test
    public void testMoveToUCI() {
        Move move = new Move("d2d4");
        assert move.toUCI().equals("d2d4");
    }
}
