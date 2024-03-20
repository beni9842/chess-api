package com.chess;

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
            case King -> 0;
        };
    }
}
