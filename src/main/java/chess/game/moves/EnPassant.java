package chess.moves;

import chess.game.Board;
import chess.pieces.Piece;
import chess.pieces.PieceColor;

public class EnPassant extends Move {
    public EnPassant(int sf, int sr, int df, int dr, Board initialBoard) {
        super(sf, sr, df, dr, initialBoard);
    }

    @Override
    public Board execute() {
        Board result = target.copy();
        result.movePiece(srcFile, srcRank, dstFile, dstRank);
        if (moverColor() == PieceColor.White) {
            result.removePiece(dstFile, dstRank+1);
        } else {
            result.removePiece(dstFile, dstRank-1);
        }
        return result;
    }

    @Override
    public Piece getCapturedPiece() {
        if (moverColor() == PieceColor.White) {
            return target.getPiece(dstFile, dstRank+1);
        } else {
            return target.getPiece(dstFile, dstRank-1);
        }
    }
}
