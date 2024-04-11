package chess.api;

import chess.game.GameInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestGameController {

    @Mock
    private GamePool gamePool;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewGame() {
        GameInterface gameInterface = mock(GameInterface.class);
        when(gamePool.NewGame()).thenReturn(gameInterface);

        ResponseEntity<String> responseEntity = gameController.newGame();

        verify(gamePool, times(1)).NewGame();
        assertEquals("Created new GameInterface with id=0", responseEntity.getBody());
    }

    @Test
    void testGetFEN() {
        int gameID = 123;
        String expectedFEN = "FEN String";
        when(gamePool.GetGameState(gameID)).thenReturn(expectedFEN);

        ResponseEntity<String> responseEntity = gameController.getFEN(gameID);

        verify(gamePool, times(1)).GetGameState(gameID);
        assertEquals(expectedFEN, responseEntity.getBody());
    }

    @Test
    void testPostMove() {
        int gameID = 456;
        String uciString = "e2e4";
        String expectedResult = "Move successful";
        when(gamePool.PostMoveToGame(gameID, uciString)).thenReturn(expectedResult);

        ResponseEntity<String> responseEntity = gameController.postMove(gameID, uciString);

        verify(gamePool, times(1)).PostMoveToGame(gameID, uciString);
        assertEquals(expectedResult, responseEntity.getBody());
    }
}
