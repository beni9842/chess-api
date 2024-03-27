import chess.game.Piece;
import chess.game.PieceColor;
import chess.game.PieceType;
import chess.game.Pieces;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestPiece {
    @Test
    void testPieceInit() {
        Piece p = new Piece(PieceColor.Black, PieceType.Queen);
        assert p.getColor() == PieceColor.Black;
        assert p.getType() == PieceType.Queen;
    }
    @Test
    void testPieceEqual() {
        Piece blackQueen = new Piece(PieceColor.Black, PieceType.Queen);
        assert blackQueen.equals(Pieces.BlackQueen());
        assert Objects.equals(blackQueen, Pieces.BlackQueen());
    }
    @Test
    void testPieceValue() {
        assert Pieces.BlackPawn().getValue() == 1;
        assert Pieces.WhiteRook().getValue() == 5;
        assert Pieces.BlackKnight().getValue() == 3;
        assert Pieces.WhiteBishop().getValue() == 3;
        assert Pieces.BlackQueen().getValue() == 9;
    }

    @Test
    void testToString() {
        assert Pieces.BlackQueen().toString().equals("q");
        assert Pieces.NoPiece().toString().equals(" ");
    }
}
