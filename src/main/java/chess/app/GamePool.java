package chess.app;

import chess.game.Game;
import chess.game.GameInterface;

import java.util.ArrayList;
import java.util.List;


public abstract class GamePool {
    private static List<GameInterface> games = new ArrayList<>();
    public static GameInterface NewGame() {
        GameInterface newGame = GameInterface.NewGameInterface();
        games.add(newGame);
        return newGame;
    }
    public static GameInterface GetGame(int id) {
        for (GameInterface gi : games) {
            if (gi.getID() == id) {
                return gi;
            }
        }
        throw new RuntimeException("No game with id=" + id);
    }
    public static String GetFEN(int id) {
        try {
            return GetGame(id).getFEN();
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public static String PostMoveUCI(int id, String uciString) {
        try {
            return GetGame(id).postMoveUCI(uciString);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public static String PostMoveSAN(int id, String sanString) {
        try {
            return GetGame(id).postMoveSAN(sanString);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public static String GetMoveRecord(int id) {
        try {
            return GetGame(id).getMoveRecord();
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
