package com.chess;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Pieces {
    @NotNull
    public static Piece BlackPawn() {
        return new Piece(PieceColor.Black, PieceType.Pawn);
    }
    @NotNull
    public static Piece BlackRook() {
        return new Piece(PieceColor.Black, PieceType.Rook);
    }
    @NotNull
    public static Piece BlackKnight() {
        return new Piece(PieceColor.Black, PieceType.Knight);
    }
    @NotNull
    public static Piece BlackBishop() {
        return new Piece(PieceColor.Black, PieceType.Bishop);
    }
    @NotNull
    public static Piece BlackKing() {
        return new Piece(PieceColor.Black, PieceType.King);
    }
    @NotNull
    public static Piece BlackQueen() {
        return new Piece(PieceColor.Black, PieceType.Queen);
    }
    @NotNull
    public static Piece WhitePawn() {
        return new Piece(PieceColor.White, PieceType.Pawn);
    }
    @NotNull
    public static Piece WhiteRook() {
        return new Piece(PieceColor.White, PieceType.Rook);
    }
    @NotNull
    public static Piece WhiteKnight() {
        return new Piece(PieceColor.White, PieceType.Knight);
    }
    @NotNull
    public static Piece WhiteBishop() {
        return new Piece(PieceColor.White, PieceType.Bishop);
    }
    @NotNull
    public static Piece WhiteKing() {
        return new Piece(PieceColor.White, PieceType.King);
    }
    @NotNull
    public static Piece WhiteQueen() {
        return new Piece(PieceColor.White, PieceType.Queen);
    }
    @Nullable
    public static Piece NoPiece() {
        return null;
    }
}
