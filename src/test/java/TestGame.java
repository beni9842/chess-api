import chess.game.Game;
import chess.game.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {

    @Test
    void testNewGame() {
        Game game = Game.NewGame();
        assertNotNull(game);
    }

    @Test
    void testCastleOptions() {
        Game game = Game.NewGame();
        // Add test cases for castleOptions()
    }

    @Test
    void testEnPassantTargets() {
        Game game = Game.NewGame();
        // Add test cases for enPassantTargets()
    }

    @Test
    void testActiveColor() {
        Game game = Game.NewGame();
        assertEquals("w", game.activeColor());
    }

    @Test
    void testAttachAndBroadcast() {
//        Game game = Game.NewGame();
//        Observer observer = new CheckObserver();
//        game.attach(observer);
//
//        // Test if the observer is added and updated correctly
//        assertTrue(game.observers.contains(observer));

        // Event event = new MovePieceEvent("e2e4", PieceColor.White);
        // game.broadcast(event);

        // Add assertions to verify if the event was broadcasted correctly
    }

    @Test
    void testGetID() {
        Game game = Game.NewGame();
        // Test if the game ID is returned correctly
        assertTrue(game.getID() > 0);
    }

    @Test
    void testGetState() {
        Game game = Game.NewGame();
        // Test if the game state string is formatted correctly
        assertNotNull(game.getState());
    }

    @Test
    void testToggleTurn() {
        Game game = Game.NewGame();
        game.toggleTurn();
        assertEquals(PieceColor.Black, game.turn);
    }

    @Test
    void testPostMove() {
        Game game = Game.NewGame();
        String result = game.postMove("e2e4");
        assertEquals("White made move: e2e4", result);

    }
}
