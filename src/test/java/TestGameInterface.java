import chess.game.Game;
import chess.game.GameInterface;
import org.junit.jupiter.api.Test;

public class TestGameInterface {
    @Test
    void testInitialState() {
        GameInterface gi = Game.NewGame();
        assert gi.getState().equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1");
    }
    @Test
    void testQueensPawnOpening() {
        GameInterface gi = Game.NewGame();
        gi.postMove("d2d4");
        assert gi.getState().equals("rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b - - 0 1");
    }

}
