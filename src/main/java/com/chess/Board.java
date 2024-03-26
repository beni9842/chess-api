package com.chess;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class Board {
    private final Piece[][] pieceArray;
    private Board() {
        pieceArray = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieceArray[i][j] = null;
            }
        }
    }
    private Board(List<List<Piece>> pieceList) {
        pieceArray = new Piece[8][8];
        if (pieceList.size() != 8) {
            throw new RuntimeException("Invalid board height");
        }
        for (int i = 0; i < 8; i++) {
            List<Piece> row = pieceList.get(i);
            if (row.size() != 8) {
                throw new RuntimeException("Invalid row width");
            }
            for (int j = 0; j < 8; j++) {
                pieceArray[i][j] = row.get(j);
            }
        }
    }
    public static boolean validIndices(int i, int j) {
        return 0 <= i && i < 8 && 0 <= j && j < 8;
    }
    @NotNull
    public Piece getPiece(int i, int j) {
        if (validIndices(i, j)) {
            return pieceArray[i][j];
        } else {
            throw new RuntimeException("Invalid indices provided to method getPiece");
        }
    }
    public Piece removePiece(int i, int j) {
        if (validIndices(i, j)) {
            Piece p = pieceArray[i][j];
            pieceArray[i][j] = Pieces.NoPiece();
            return p;
        } else {
            throw new RuntimeException("Invalid indices provided to method removePiece");
        }
    }
    public void setPiece(Piece newPiece, int i, int j) {
        if (validIndices(i, j)) {
            pieceArray[i][j] = newPiece;
        } else {
            throw new RuntimeException("Invalid indices provided to method setPiece");
        }
    }
    public static Board NewBoard() {
        return new Board(Arrays.asList(
                Arrays.asList( Pieces.BlackRook(), Pieces.BlackKnight(), Pieces.BlackBishop(), Pieces.BlackQueen(), Pieces.BlackKing(), Pieces.BlackBishop(), Pieces.BlackKnight(), Pieces.BlackRook() ),
                Arrays.asList( Pieces.BlackPawn(), Pieces.BlackPawn(), Pieces.BlackPawn(), Pieces.BlackPawn(), Pieces.BlackPawn(), Pieces.BlackPawn(), Pieces.BlackPawn(), Pieces.BlackPawn() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.WhitePawn(), Pieces.WhitePawn(), Pieces.WhitePawn(), Pieces.WhitePawn(), Pieces.WhitePawn(), Pieces.WhitePawn(), Pieces.WhitePawn(), Pieces.WhitePawn() ),
                Arrays.asList( Pieces.WhiteRook(), Pieces.WhiteKnight(), Pieces.WhiteBishop(), Pieces.WhiteQueen(), Pieces.WhiteKing(), Pieces.WhiteBishop(), Pieces.WhiteKnight(), Pieces.WhiteRook() )
        ));
    }
    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        int noPieceCounter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece currPiece = getPiece(i, j);
                if (currPiece.equals(Pieces.NoPiece())) {
                    noPieceCounter += 1;
                } else {
                    if (noPieceCounter > 0) {
                        boardString.append(noPieceCounter);
                    }
                    noPieceCounter = 0;
                    boardString.append(currPiece.toString());
                }
            }
            if (noPieceCounter > 0) {
                boardString.append(noPieceCounter);
            }
            noPieceCounter = 0;
            if (i != 7) {
                boardString.append("/");
            }
        }
        return boardString.toString();
    }
}
