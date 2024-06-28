package chess.moves;

import chess.game.Board;

public class Castle extends Move {

    public Castle(int sf, int sr, int df, int dr, Board initialBoard) {
        super(sf, sr, df, dr, initialBoard);
    }

    @Override
    public Board execute() {
        Board result = target.copy();
        result.movePiece(srcFile, srcRank, dstFile, dstRank);
        if (dstFile == 6) {
            // king-side castle
            result.movePiece(7, srcRank, 5, dstRank);
        }
        if (dstFile == 2) {
            // queen-side castle
            result.movePiece(0, srcRank, 3, dstRank);
        }
        return result;
    }

    @Override
    public String toSAN() {
        if (dstFile == 6) {
            // king-side castle
            return "O-O";
        }
        if (dstFile == 2) {
            // queen-side castle
            return "O-O-O";
        }
        else {
            throw new RuntimeException("Illegal castle encountered.");
        }
    }
}
