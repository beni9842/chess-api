package chess.game;

public class Piece {
    private final PieceColor color;
    private final PieceType type;
    public Piece(PieceColor pieceColor, PieceType pieceType) {
        color = pieceColor;
        type = pieceType;
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Piece) {
            Piece that = (Piece) obj;
            return this.color == that.color && this.type == that.type;
        }
        return false;
    }

    public int getValue() {
        return switch (type) {
            case Pawn -> 1;
            case Rook -> 5;
            case Knight, Bishop -> 3;
            case Queen -> 9;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return switch (type) {
            case Pawn -> switch (color) {
                case Black -> "p";
                case White -> "P";
                default -> " ";
            };
            case Rook -> switch (color) {
                case Black -> "r";
                case White -> "R";
                default -> " ";
            };
            case Knight -> switch (color) {
                case Black -> "n";
                case White -> "N";
                default -> " ";
            };
            case Bishop -> switch (color) {
                case Black -> "b";
                case White -> "B";
                default -> " ";
            };
            case Queen -> switch (color) {
                case Black -> "q";
                case White -> "Q";
                default -> " ";
            };
            case King -> switch (color) {
                case Black -> "k";
                case White -> "K";
                default -> " ";
            };
            default -> " ";
        };
    }
}
