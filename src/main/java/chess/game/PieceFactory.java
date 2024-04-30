package chess.game;

public class PieceFactory {
    public static Piece createPiece(PieceColor color, PieceType type) {
        return new Piece(color, type);
    }
}