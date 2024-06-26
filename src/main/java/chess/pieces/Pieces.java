package chess.pieces;

public interface Pieces {
    static Piece WhitePawn() {
        return new Pawn(PieceColor.White);
    }
    static Piece BlackPawn() {
        return new Pawn(PieceColor.Black);
    }
    static Piece WhiteRook() {
        return new Rook(PieceColor.White);
    }
    static Piece BlackRook() {
        return new Rook(PieceColor.Black);
    }
    static Piece WhiteKnight() {
        return new Knight(PieceColor.White);
    }
    static Piece BlackKnight() {
        return new Knight(PieceColor.Black);
    }
    static Piece WhiteBishop() {
        return new Bishop(PieceColor.White);
    }
    static Piece BlackBishop() {
        return new Bishop(PieceColor.Black);
    }
    static Piece WhiteQueen() {
        return new Queen(PieceColor.White);
    }
    static Piece BlackQueen() {
        return new Queen(PieceColor.Black);
    }
    static Piece WhiteKing() {
        return new King(PieceColor.White);
    }
    static Piece BlackKing() {
        return new King(PieceColor.Black);
    }
    static Piece NoPiece() {
        return new NoPiece();
    }
}
