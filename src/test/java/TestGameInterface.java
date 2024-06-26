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
        gi.postMoveSAN("Qxf3");
        gi.postMoveSAN("dxe5");
        gi.postMoveSAN("Bc4");
        gi.postMoveSAN("Nf6");
        gi.postMoveSAN("Qb3");
        gi.postMoveSAN("Qe7");
        gi.postMoveSAN("Nc3");
        gi.postMoveSAN("c6");
        assertEquals("rn2kb1r/pp2qppp/2p2n2/4p3/2B1P3/1QN5/PPP2PPP/R1B1K2R w KQkq - 0 9", gi.getFEN());
        gi.postMoveSAN("Bg5");
        gi.postMoveSAN("b5");
        gi.postMoveSAN("Nxb5");
        gi.postMoveSAN("cxb5");
        gi.postMoveSAN("Bxb5+");
        assertEquals("Black made move Nbd7", gi.postMoveSAN("Nbd7"));
        assertEquals("r3kb1r/p2nqppp/5n2/1B2p1B1/4P3/1Q6/PPP2PPP/R3K2R w KQkq - 1 12", gi.getFEN());
        assertEquals("White made move O-O-O", gi.postMoveSAN("O-O-O"));
        assertEquals("r3kb1r/p2nqppp/5n2/1B2p1B1/4P3/1Q6/PPP2PPP/2KR3R b kq - 2 12", gi.getFEN());
        gi.postMoveSAN("Rd8");
        gi.postMoveSAN("Rxd7");
        gi.postMoveSAN("Rxd7");
        gi.postMoveSAN("Rd1");
        gi.postMoveSAN("Qe6");
        gi.postMoveSAN("Bxd7+");
        gi.postMoveSAN("Nxd7");
        gi.postMoveSAN("Qb8+");
        gi.postMoveSAN("Nxb8");
        gi.postMoveSAN("Rd8#");
        assertEquals("1n1Rkb1r/p4ppp/4q3/4p1B1/4P3/8/PPP2PPP/2K5 b k - 1 17", gi.getFEN());
    }

    @Test
    void testSimpleEnPassant() {
        GameInterface gi = GameInterface.NewGameInterface();
        gi.postMoveSAN("e4");
        gi.postMoveSAN("e6");
        gi.postMoveSAN("e5");
        gi.postMoveSAN("d5");
        assertEquals("White made move exd6", gi.postMoveSAN("exd6"));
        assertEquals("rnbqkbnr/ppp2ppp/3Pp3/8/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 3", gi.getFEN());
    }
}