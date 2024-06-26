package chess.game;

import chess.pieces.PieceColor;

import java.util.ArrayList;

public interface GameInterface {
    String getFEN();
    String postMoveUCI(String uciString);
    String postMoveSAN(String sanString);
    int getID();

    static GameInterface NewGameInterface() {
        return Game.NewGame();
    }
}
