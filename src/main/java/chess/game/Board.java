package chess.game;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    public static int RankInt(char rankChar) {
        if ('1' <= rankChar && rankChar <= '8') {
            return 8 - (rankChar - '0');
        } else {
            return -1;
        }
    }
    public static int FileInt(char fileChar) {
        if ('a' <= fileChar && fileChar <= 'h') {
            return fileChar - 'a';
        } else {
            return -1;
        }
    }
    public static String RankStr(int rankInt) {
        if (0 <= rankInt && rankInt < 8) {
            return "" + (char) ('8' - rankInt);
        } else {
            return "";
        }
    }
    public static String FileStr(int fileInt) {
        if (0 <= fileInt && fileInt < 8) {
            return "" + (char) ('a' + fileInt);
        } else {
            return "";
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
    public static Board EmptyBoard() {
        return new Board(Arrays.asList(
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() ),
                Arrays.asList( Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece(), Pieces.NoPiece() )
        ));
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

    public Board copy() {
        List<List<Piece>> pieceList = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            pieceList.add(new ArrayList<>(8));
            for (int j = 0; j < 8; j++) {
                pieceList.get(i).add(pieceArray[i][j].copy());
            }
        }
        return new Board(pieceList);
    }
}
