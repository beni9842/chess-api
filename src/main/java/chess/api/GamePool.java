package chess.api;

import chess.game.Game;
import chess.game.GameInterface;

import java.util.ArrayList;
import java.util.List;


public abstract class GamePool {
    private static List<GameInterface> games = new ArrayList<>();
    public static GameInterface NewGame() {
        GameInterface newGame = Game.NewGame();
        games.add(newGame);
        return newGame;
    }
    public static String GetGameState(int id) {
        for (GameInterface gi : games) {
            if (gi.getID() == id) {
                return gi.getState();
            }
        }
        return "Error: no game with id=" + id;
    }

    public static String PostMoveToGame(int id, String uciString) {
        for (GameInterface gi : games) {
            if (gi.getID() == id) {
                return gi.postMove(uciString);
            }
        }
            return "Error: no game with id=" + id;
    }
}
