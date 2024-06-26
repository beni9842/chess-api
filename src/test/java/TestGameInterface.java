import chess.game.Game;
import chess.game.GameInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameInterface {
    @Test
    void testInitialState() {
        GameInterface gi = GameInterface.NewGameInterface();
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", gi.getFEN());
    }
    @Test
    void testQueensPawnOpening() {
        GameInterface gi = GameInterface.NewGameInterface();
        gi.postMoveUCI("d2d4");
        assertEquals("rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq d3 0 1", gi.getFEN());
    }
    @Test
    void testScotchGame() {
        GameInterface gi = GameInterface.NewGameInterface();
        gi.postMoveUCI("e2e4");
        gi.postMoveUCI("e7e5");
        gi.postMoveUCI("g1f3");
        gi.postMoveUCI("b8c6");
        gi.postMoveUCI("d2d4");
        assertEquals("r1bqkbnr/pppp1ppp/2n5/4p3/3PP3/5N2/PPP2PPP/RNBQKB1R b KQkq d3 0 3", gi.getFEN());
    }
    @Test
    void testOperaGame() {
        GameInterface gi = GameInterface.NewGameInterface();
        gi.postMoveSAN("e4");
        gi.postMoveSAN("e5");
        assertEquals("White made move Nf3", gi.postMoveSAN("Nf3"));
        gi.postMoveSAN("d6");
        assertEquals("rnbqkbnr/ppp2ppp/3p4/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 0 3", gi.getFEN());
        gi.postMoveSAN("d4");
        gi.postMoveSAN("Bg4");
        assertEquals("White made move dxe5", gi.postMoveSAN("dxe5"));
        assertEquals("Black made move Bxf3", gi.postMoveSAN("Bxf3"));
        assertEquals("rn1qkbnr/ppp2ppp/3p4/4P3/4P3/5b2/PPP2PPP/RNBQKB1R w KQkq - 0 5", gi.getFEN());
    }
}