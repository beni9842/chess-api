package chess.moves;

import chess.game.Board;
import chess.game.Game;
import chess.game.GameObserver;
import chess.pieces.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Move {
    protected final int srcFile;
    protected final int srcRank;
    protected final int dstFile;
    protected final int dstRank;
    protected final Board target;
    private final Piece movingPiece;
    public Move(int sf, int sr, int df, int dr, Board initialBoard) {
        srcFile = sf;
        srcRank = sr;
        dstFile = df;
        dstRank = dr;
        target = initialBoard;
        movingPiece = getMovingPiece();
    }
    public List<Integer> getMoveVector() {
        return Arrays.asList(srcFile, srcRank, dstFile, dstRank);
    }
    public PieceColor moverColor() {
        return getMovingPiece().getColor();
    }
    public PieceColor otherColor() {
        PieceColor otherColor = PieceColor.White;
        if (moverColor() == PieceColor.White) {
            otherColor = PieceColor.Black;
        }
        return otherColor;
    }
    public boolean check() {
        return GameObserver.InCheck(otherColor(), execute(), "-", "-");
    }
    public boolean checkmate() {
        return GameObserver.InCheckmate(otherColor(), execute(), "-", "-");
    }
    public String toUCI() {
        return Board.FileString(srcFile) + Board.RankString(srcRank) + Board.FileString(dstFile) + Board.RankString(dstRank);
    }
    public String toSAN() {
        StringBuilder san = new StringBuilder();
        Piece movingPiece = getMovingPiece();
        san.append(movingPiece.toSAN());
        List<Move> legalMoves = LegalMoves.GetLegalMoves(target, moverColor(), "-", "-");
        for (Move m : legalMoves) {
            if (m.dstFile == dstFile && m.dstRank == dstRank && !m.equals(this) && m.getMovingPiece().getType() == getMovingPiece().getType()) {
                if (m.srcFile == srcFile) { // on same file
                    // add rank to move
                    san.append(Board.RankString(srcRank));
                } else { // on same rank or not on same rank or file
                    // add file to move
                    san.append(Board.FileString(srcFile));
                }
            }
        }
        if (getCapturedPiece().getColor() == otherColor()) {
            if (movingPiece.getType() == PieceType.Pawn) {
                san.append(Board.FileString(srcFile));
            }
            san.append("x");
        }
        san.append(Board.FileString(dstFile)).append(Board.RankString(dstRank));
        if (checkmate()) {
            san.append("#");
        } else if (check()) {
            san.append("+");
        }
        return san.toString();
    }
    public Piece getCapturedPiece() {
        return target.getPiece(dstFile, dstRank).copy();
    }
    public Piece getMovingPiece() {
        return target.getPiece(srcFile, srcRank).copy();
    }
    public Board execute() {
        Board result = target.copy();
        result.movePiece(srcFile, srcRank, dstFile, dstRank);
        return result;
    }

    public String epTargetString() {
        return "-";
    }

    @Override
    public String toString() {
        return toSAN();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Move) {
            Move otherMove = (Move) other;
            return srcFile == otherMove.srcFile &&
                    srcRank == otherMove.srcRank &&
                    dstFile == otherMove.dstFile &&
                    dstRank == otherMove.dstRank;
        }
        return false;
    }
}
