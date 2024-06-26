package chess.moves;

import chess.game.Board;
import chess.game.Game;
import chess.game.GameObserver;
import chess.pieces.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Move {
    private final int srcFile;
    private final int srcRank;
    private final int dstFile;
    private final int dstRank;
    private final Board target;
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
        return GameObserver.InCheck(otherColor(), execute());
    }
    public boolean checkmate() {
        return GameObserver.InCheck(otherColor(), execute());
    }
    public String toUCI() {
        return Board.FileString(srcFile) + Board.RankString(srcRank) + Board.FileString(dstFile) + Board.RankString(dstRank);
    }
    public String toSAN() {
        StringBuilder san = new StringBuilder();
        Piece movingPiece = getMovingPiece();
        san.append(movingPiece.toSAN());
        List<Move> legalMoves = LegalMoves.GetLegalMoves(target, moverColor());
        for (Move m : legalMoves) {
            if (m.getMoveVector().subList(2,4) == getMoveVector().subList(2,4) && m.getMovingPiece().getType() == getMovingPiece().getType()) {
                if (m.getMoveVector().get(0) == getMoveVector().get(0)) {
                    san.append(Board.RankString(srcRank));
                } else {
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
        Piece movingPiece = getMovingPiece();
        Piece capturedPiece = getCapturedPiece();
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
}
