package chess.game;

public enum PieceColor {
    Black, White, NoColor;

    public static PieceColor negate(PieceColor color) {
        if (color == Black) {
            return White;
        } else if (color == White) {
            return Black;
        } else {
            return NoColor;
        }
    }
}
