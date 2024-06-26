package chess.game;

import chess.pieces.NoPiece;
import chess.pieces.Piece;
import chess.pieces.Pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    Piece[][] pieceArray;
    private Board(List<List<Piece>> pieceList) {
        pieceArray = new Piece[8][8];
        if (pieceList.size() != 8) {
            throw new RuntimeException("Invalid height for pieceList provided to Board constructor");
        }
        for (int i = 0; i < 8; i++) {
            List<Piece> row = pieceList.get(i);
            if (row.size() != 8) {
                throw new RuntimeException("Invalid height for row " + i + " provided to Board constructor");
            }
            for (int j = 0; j < 8; j++) {
                pieceArray[i][j] = row.get(j);
            }
        }
    }

    public Board copy() {
        List<List<Piece>> pieceList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            pieceList.add(Arrays.asList(pieceArray[i]));
        }
        return new Board(pieceList);
    }
    public static boolean validFileAndRank(int file, int rank) {
        return file >= 0 && file < 8 && rank >= 0 && rank < 8;
    }
    public void setPiece(Piece piece, int file, int rank) {
        if (validFileAndRank(file, rank)) {
            pieceArray[file][rank] = piece;
        } else {
            throw new RuntimeException("Invalid file or rank provided to setPiece");
        }
    }
    public Piece getPiece(int file, int rank) {
        if (validFileAndRank(file, rank)) {
            return pieceArray[file][rank];
        } else {
            throw new RuntimeException("Invalid file or rank provided to getPiece");
        }
    }
    public Piece removePiece(int file, int rank) {
        if (validFileAndRank(file, rank)) {
            Piece p = pieceArray[file][rank];
            pieceArray[file][rank] = Pieces.NoPiece();
            return p;
        } else {
            throw new RuntimeException("Invalid file or rank provided to removePiece");
        }
    }
    public void movePiece(int srcFile, int srcRank, int dstFile, int dstRank) {
        Piece p = removePiece(srcFile, srcRank);
        setPiece(p, dstFile, dstRank);
    }
    public static Board NewBoard() {
        return new Board(Arrays.asList(
                Arrays.asList(Pieces.BlackRook(), Pieces.BlackPawn(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.WhitePawn(),Pieces.WhiteRook()),
                Arrays.asList(Pieces.BlackKnight(), Pieces.BlackPawn(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.WhitePawn(), Pieces.WhiteKnight()),
                Arrays.asList(Pieces.BlackBishop(), Pieces.BlackPawn(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.WhitePawn(), Pieces.WhiteBishop()),
                Arrays.asList(Pieces.BlackQueen(), Pieces.BlackPawn(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.WhitePawn(), Pieces.WhiteQueen()),
                Arrays.asList(Pieces.BlackKing(), Pieces.BlackPawn(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.WhitePawn(), Pieces.WhiteKing()),
                Arrays.asList(Pieces.BlackBishop(), Pieces.BlackPawn(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.WhitePawn(), Pieces.WhiteBishop()),
                Arrays.asList(Pieces.BlackKnight(), Pieces.BlackPawn(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.WhitePawn(), Pieces.WhiteKnight()),
                Arrays.asList(Pieces.BlackRook(), Pieces.BlackPawn(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.WhitePawn(), Pieces.WhiteRook())
        ));
    }

    public static Board EmptyBoard() {
        return new Board(Arrays.asList(
                Arrays.asList(Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(),Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece()),
                Arrays.asList(Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(),Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece()),
                Arrays.asList(Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(),Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece()),
                Arrays.asList(Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(),Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece()),
                Arrays.asList(Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(),Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece()),
                Arrays.asList(Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(),Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece()),
                Arrays.asList(Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(),Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece()),
                Arrays.asList(Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(),Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece())
        ));
    }
    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        int nSpaces = 0;
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece p = pieceArray[file][rank];
                if (p.equals(Pieces.NoPiece())) {
                    nSpaces += 1;
                } else {
                    if (nSpaces > 0) {
                        boardString.append(nSpaces);
                        nSpaces = 0;
                    }
                    boardString.append(p);
                }
            }
            if (nSpaces > 0) {
                boardString.append(nSpaces);
                nSpaces = 0;
            }
            if (rank < 7) {
                boardString.append("/");
            }
        }
        return boardString.toString();
    }
    public static String RankString(int rankInt) {
        if (validFileAndRank(0, rankInt)) {
            return "" + (char) ('8' - rankInt);
        } else {
            throw new RuntimeException("Invalid rankInt provided to RankString");
        }
    }
    public static String FileString(int fileInt) {
        if (validFileAndRank(fileInt, 0)) {
            return "" + (char) ('a' + fileInt);
        } else {
            throw new RuntimeException("Invalid fileInt provided to FileString");
        }
    }
    public static int RankInt(String rankStr) {
        if (rankStr.length() == 1) {
            char rankChar = rankStr.charAt(0);
            int rankInt = 8 - (rankChar - '0');
            if (validFileAndRank(0, rankInt)) {
                return rankInt;
            }
        }
        throw new RuntimeException("Invalid rankStr provided to RankInt");
    }
    public static int FileInt(String fileStr) {
        if (fileStr.length() == 1) {
            char fileChar = fileStr.charAt(0);
            int fileInt = fileChar - 'a';
            if (validFileAndRank(0, fileInt)) {
                return fileInt;
            }
        }
        throw new RuntimeException("Invalid fileStr provided to FileInt");
    }
}
