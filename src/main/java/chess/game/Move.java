package chess.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Move {
    private final List<Integer> moveVector;
    public Move(int srcFile, int srcRank, int dstFile, int dstRank) {
        moveVector = Arrays.asList(srcFile, srcRank, dstFile, dstRank);
    }
    public Move(String uciString) {
        if (uciString.length() == 4) {
            moveVector = new ArrayList<>(Arrays.asList(
                    Board.FileInt(uciString.charAt(0)),
                    Board.RankInt(uciString.charAt(1)),
                    Board.FileInt(uciString.charAt(2)),
                    Board.RankInt(uciString.charAt(3))
            ));
        } else {
            throw new RuntimeException("Cannot generate move from uciString");
        }
    }
    public void execute(Board target) {
        Piece p = target.removePiece(moveVector.get(1), moveVector.get(0));
        target.setPiece(p, moveVector.get(3), moveVector.get(2));
    }
    public String toUCI() {
        return ( Board.FileStr(moveVector.get(0)) +
                 Board.RankStr(moveVector.get(1)) +
                 Board.FileStr(moveVector.get(2)) +
                 Board.RankStr(moveVector.get(3))
        );
    }

    public Piece capturedPiece(Board target) {
        return target.getPiece(moveVector.get(3), moveVector.get(2)).copy();
    }
    public Piece movingPiece(Board target) {
        return target.getPiece(moveVector.get(1), moveVector.get(0)).copy();
    }
    public List<Integer> getMoveVector() {
        return moveVector;
    }
}
