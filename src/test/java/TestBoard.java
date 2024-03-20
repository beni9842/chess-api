import com.chess.Board;
import org.junit.jupiter.api.Test;

public class TestBoard {
    @Test
    void testNewBoard() {
        Board newBoard = Board.NewBoard();
        assert newBoard.getPiece(2,2) == null;
    }
}
