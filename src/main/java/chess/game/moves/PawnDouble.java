package chess.moves;

import chess.game.Board;
import chess.pieces.Piece;
import chess.pieces.PieceColor;

import java.util.List;

public class PawnDouble extends Move {
    public PawnDouble(int sf, int sr, int df, int dr, Board target) {
        super(sf, sr, df, dr, target);
    }
    @Override
    public String epTargetString() {
        Piece movingPiece = getMovingPiece();
        PieceColor color = movingPiece.getColor();
        List<Integer> moveVector = getMoveVector();
        int targetFile = moveVector.get(2);
        int targetRank;
        if (color == PieceColor.White) {
            targetRank = moveVector.get(3) + 1;
        } else {
            // color assumed to be black
            targetRank = moveVector.get(3) - 1;
        }
        return Board.FileString(targetFile) + Board.RankString(targetRank);
    }
}
